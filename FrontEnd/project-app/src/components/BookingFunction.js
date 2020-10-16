import React, { Component } from 'react'
import axios from 'axios'
import { Card, Table, Modal, Button, message, Select, Form, Input, DatePicker } from 'antd'
import 'antd/dist/antd.css'

const Option = Select.Option

export default class BookingFunc extends Component {

    initColumn = () => {
        this.columns = [
            {
                title: "Booking Id",
                dataIndex: 'id'
            },
            {
                title: "Create Date",
                dataIndex: 'bookingCreationDate'
            },
            {
                title: "Service",
                dataIndex: 'service'
            },
            {
                title: "Service Date",
                dataIndex: 'bookingDate'
            },
            {
                title: "Assigned Employee",
                dataIndex: 'assignedEmployee'
            },
            {
                title: "Process",
                render: (booking) => (
                    <span>
                        <Button type="primary" onClick={() => this.deleteEmployee(booking)}>Cancel booking</Button>
                    </span>
                )
            }
        ]
    }
    constructor() {
        super();

        this.state = {
            visible: false,
            bookingList: [],
            isShow: false,
            searchType: 'id',
            searchInput: '',
            searchDateInput: ''

        };
    }

    deleteEmployee = (booking) => {
        var now = new Date();
        var serviceTime = new Date(booking.bookingDate)
        if (serviceTime.getTime() - now.getTime() >= (1000 * 3600 * 24) * 2) {  //(1000 * 3600 * 24) == 1day
            Modal.confirm({
                title: `Do you want delete booking ${booking.id}?`,
                onOk: async () => {
                    console.log(booking.id);
                    const result = await axios.delete("http://localhost:8080/api/bookings/" + `${booking.id}`);
                    if (result.status === 200) {
                        console.log('Success');
                        message.success({
                            content: 'Cancel success',
                            className: 'custom-class',
                            style: {
                                marginTop: '20vh',
                            },
                        });
                    }
                }
            })
        } else {
            message.error({
                content: 'Cancel fail. Service date is less than 2 days. Please contact admin if you want cancel',
                className: 'custom-class',
                style: {
                    marginTop: '20vh',
                },
            })
        }

    };

    editEmployee = (employee) => {
        this.employee = employee
        this.setState({
            isShow: true
        })
    };

    getBookingList = async () => {
        const searchInput = this.state.searchInput;
        const searchDateInput = this.state.searchDateInput;
        const searchType = this.state.searchType;
        if (searchType === "id") {
            const result = await axios.get("http://localhost:8080/api/bookings?" + `${searchType}` + '=' + `${searchInput}`)
            if (result.status == 200) {
                this.state.bookingList.push(result.data)
            }
            // } else if (searchType === "bookingDate") {
            //     console.log(searchDateInput)
            //     const result = await axios.get("http://localhost:8080/api/bookings?" + `${searchType}` + '=' + `${searchDateInput}`)
            //     if (result.status === 200) {
            //         const searchbookingList = result.data
            //         this.setState({
            //             bookingList: searchbookingList
            //         })
            //     }
        } else {
            const result = await axios.get("http://localhost:8080/api/bookings?" + `${searchType}` + '=' + `${searchInput}`)
            if (result.status === 200) {
                const searchbookingList = result.data
                this.setState({
                    bookingList: searchbookingList
                })
            }
        }

    }

    componentDidMount() {
        this.initColumn();
        axios.get("http://localhost:8080/api/bookings")
            .then(response => {
                const bookingList = response.data
                this.setState({
                    bookingList
                })
            })
    }

    render() {
        const { isShow, searchDateInput, searchType, searchInput } = this.state
        const booking = this.booking || {}
        const searchBar = (
            <span >
                <Select value={searchType} style={{ width: 200 }} onChange={value => this.setState({ searchType: value })}>
                    <Option value='id'>Search By booking id</Option>
                    <Option value='service'>Search By service</Option>
                    {/* <Option value='bookingDate'>Search By service date</Option> */}
                    <Option value='assignedEmployee'>Search By assigned employee</Option>
                </Select>
                <Input placeholder='Relevant information' style={{ width: 250 }} value={searchInput} onChange={event => this.setState({ searchInput: event.target.value })} />
                {/* <DatePicker placeholder='Date selection for date searching' style={{ width: 250 }} value={searchDateInput} onChange={value => this.setState({ searchDateInput: value })} /> */}
                <Button type='primary' onClick={() => this.getBookingList()}>Search</Button>
                <Button type='primary' onClick={() => this.componentDidMount()}>Reset</Button>
            </span>
        );

        return (
            <Card title={searchBar}>
                <Table bordered columns={this.columns} rowKey='id' dataSource={this.state.bookingList} rowSelection={{ type: 'radio' }} onRow={this.onRow} />


            </Card >

        )
    }

}
