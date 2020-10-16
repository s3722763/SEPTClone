import React, { Component, PureComponent } from 'react'
import { Form, Input, Radio } from 'antd'
import PropTypes from 'prop-types'

const Item = Form.Item

class EmployeeForm extends Component {

    static propTypes = {
        setForm: PropTypes.func.isRequired,
        employee: PropTypes.object
    }

    componentWillMount() {
        this.props.setForm(this.props.form)
    }

    render() {
        const { getFieldDecorator } = this.props.form
        const employee = this.props.employee || {}
        const formItemLayout = {
            labelCol: {
                xs: { span: 24 },
                sm: { span: 8 },
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 16 },
            },
        };

        var dobInput = employee.dateOfBirth;
        var dobOutput = dobInput.replace(/(\d{4})\/(\d\d)\/(\d\d)/, "$1-$2-$3");

        return (

            <Form {...formItemLayout}>
                <Item label='Employee Name'>
                    {
                        getFieldDecorator('name', {
                            initialValue: employee.name
                        })(
                            <Input style={{ width: '100%' }} required />
                        )
                    }
                </Item>
                <Item label='DOB'>
                    {
                        getFieldDecorator('dateOfBirth', {
                            initialValue: dobOutput
                        })(
                            <Input type="date" style={{ width: '100%' }} required />

                        )
                    }
                </Item>
                <Item label='Gender'>
                    {
                        getFieldDecorator('gender', {
                            initialValue: employee.gender
                        })(
                            <Radio.Group required>
                                <Radio value="male">Male</Radio>
                                <Radio value="female">Female</Radio>
                                <Radio value="other">Other</Radio>
                            </Radio.Group>
                        )
                    }
                </Item>
                <Item label='Email'>
                    {
                        getFieldDecorator('email', {
                            initialValue: employee.email
                        })(
                            <Input style={{ width: '100%' }} required />
                        )
                    }
                </Item>
                <Item label='Phone No'>
                    {
                        getFieldDecorator('phoneNumber', {
                            initialValue: employee.phoneNumber
                        })(
                            <Input style={{ width: '100%' }} required />
                        )
                    }
                </Item>
                <Item label='TFN'>
                    {
                        getFieldDecorator('tfn', {
                            initialValue: employee.tfn
                        })(
                            <Input style={{ width: '100%' }} required />
                        )
                    }
                </Item>
                <Item label='Super'>
                    {
                        getFieldDecorator('superNumber', {
                            initialValue: employee.superNumber
                        })(
                            <Input style={{ width: '100%' }} required />
                        )
                    }
                </Item>

            </Form>
        )
    }
}
const EditForm = Form.create()(EmployeeForm)
export default EditForm