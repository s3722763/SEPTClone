import React, { Component } from 'react'
import axios from 'axios'
import { Card, Table, Modal, Button, message, Select, Form } from 'antd'
import EmployeeForm from './Employee-form'
import 'antd/dist/antd.css'

export default class DelPerson extends Component {

    initColumn = () => {
        this.columns = [
            {
                title: "EmployeeName",
                dataIndex: 'name'
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
            isShow: false

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
        const { isShow } = this.state
        const employee = this.employee || {}

        return (
            <Card >
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

            </Card>

        )
    }

}
