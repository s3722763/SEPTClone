import React, { Component } from 'react'
import axios from 'axios'
import { Card, Table, Modal, Button, message, Select, Form, Input } from 'antd'
import EmployeeForm from './Employee-form.js'
import 'antd/dist/antd.css'

const Option = Select.Option

export default class DelPerson extends Component {

    initColumn = () => {
        this.columns = [
            {
                title: "EmployeeName",
                dataIndex: 'name'
            },
            {
                title: "DOB",
                dataIndex: 'dateOfBirth'
            },
            {
                title: "Email",
                dataIndex: 'email'
            },
            {
                title: "Gender",
                dataIndex: 'gender'
            },
            {
                title: "Phone Number",
                dataIndex: 'phoneNumber'
            },
            {
                title: "Process",
                render: (employee) => (
                    <span>
                        <Button type="primary" onClick={() => this.editEmployee(employee)}>Edit</Button>
                        <Button type="primary" onClick={() => this.deleteEmployee(employee)}>Delete</Button>
                    </span>
                )
            }
        ]
    }
    constructor() {
        super();

        this.state = {
            visible: false,
            employeeList: [],
            isShow: false,
            searchType: 'name',
            searchInput: ''

        };
    }

    hideModal = () => {
        this.setState({
            visible: false,
        })
    }

    deleteEmployee = (employee) => {
        Modal.confirm({
            title: `Do you want delete ${employee.name}?`,
            onOk: async () => {
                console.log(employee.id);
                const result = await axios.delete("http://localhost:8080/api/employee?id=" + `${employee.id}`);
                if (result.status === 200) {
                    console.log('Success');
                    message.success({
                        content: 'Delete success',
                        className: 'custom-class',
                        style: {
                            marginTop: '20vh',
                        },
                    });
                }
            }
        })
    };

    editEmployee = (employee) => {
        this.employee = employee
        this.setState({
            isShow: true
        })
    };

    updateEmployee = async () => {

        this.setState({ isShow: false })
        const employee = this.form.getFieldsValue()
        this.form.resetFields()
        console.log(employee)
        if (this.employee) {
            employee.id = this.employee.id
        }

        const result = await axios.put("http://localhost:8080/api/employee?", employee);
        if (result.status === 200) {
            console.log('Success');
            message.success({
                content: `${this.employee.name} edit success`,
                className: 'custom-class',
                style: {
                    marginTop: '20vh',
                },
            });
        }
        if (result.status === 400) {
            message.error({
                content: 'Something wrong. Please check the input',
            })
        }

    }

    getEmployeeList = async () => {
        const searchInput = this.state.searchInput;
        const searchType = this.state.searchType;
        const result = await axios.get("http://localhost:8080/api/employee?" + `${searchType}` + '=' + `${searchInput}`)
        if (result.status == 200) {
            const searchEmployeeList = result.data
            this.setState({
                employeeList: searchEmployeeList
            })
        }
    }

    componentDidMount() {
        this.initColumn();
        axios.get("http://localhost:8080/api/employee")
            .then(response => {
                const employeeList = response.data
                this.setState({
                    employeeList
                })

            })
    }

    render() {
        const { isShow, searchType, searchInput } = this.state
        const employee = this.employee || {}

        const searchBar = (
            <span>
                <Select value={searchType} style={{ width: 200 }} onChange={value => this.setState({ searchType: value })}>
                    <Option value='name'>Search By name</Option>
                    <Option value='email'>Search By email</Option>
                    <Option value='gender'>Search By gender</Option>
                    <Option value='phoneNumber'>Search By phone number</Option>
                </Select>
                <Input placeholder='Relevant information' style={{ width: 250 }} value={searchInput} onChange={event => this.setState({ searchInput: event.target.value })} />
                <Button type='primary' onClick={() => this.getEmployeeList()}>Search</Button>
                <Button type='primary' onClick={() => this.componentDidMount()}>Reset</Button>
            </span>
        );

        return (
            <Card title={searchBar} extra={<a href="#">More</a>}>

                <Table bordered columns={this.columns} rowKey='id' dataSource={this.state.employeeList} rowSelection={{ type: 'radio' }} onRow={this.onRow} />

                <Modal
                    title={employee.name ? 'Edit employee' : ''}
                    visible={isShow}
                    onOk={this.updateEmployee}
                    onCancel={() => this.setState({ isShow: false })}
                >
                    <EmployeeForm
                        setForm={form => this.form = form}
                        employee={employee}
                    />
                </Modal>

            </Card >

        )
    }

}
