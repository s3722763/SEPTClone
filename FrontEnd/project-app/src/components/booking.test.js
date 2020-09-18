import React from "react";
import Booking from './Booking';
import {shallow, mount} from "enzyme";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";

Enzyme.configure({adapter: new Adapter() });

describe("<Booking/> component Unit Test", () =>{
    it('Should capture booker full name correctly onChange', () => {
      const component = mount(<Booking />);
      const input = component.find('input').at(0);
      input.instance().value = 'Joanne Grech';
      input.simulate('change');
      expect(component.state().bookersName).toEqual('Joanne Grech');
    });

    it('invokes datetime onChange handler', () => {
      const component = mount(<Booking />);
      const input = component.find('input').at(1);
      input.instance().value = '2020-09-17T08:30';
      input.simulate('change');
      expect(component.state().bookingDate).toEqual('2020-09-17T08:30');
    })

    it('should display correct worker', () =>{
      const component = mount(<Booking/>)
      // const input = component.find('select').at(1);
      // input.instance().value = input.find('option').at(1);
      // // Then its default value is 8:00
      component.find('select').at(1).simulate('change', {target: {value: 'sandra'}})
      expect(component.find('select').at(1).props().value).toBe('sandra')
    });
    
    
})

describe('<Booking /> Submit form', () => {
  const testValues = {
    bookersName: "Joanne Grech",
    workerName: "Sandra",
    bookingDate: "2020-09-17T08:30",
    service: "Service 1",
    handleSubmit: jest.fn(),
  };

  it('Submit should work', () => {

      const component = mount(
          <Booking {...testValues} />
      );
      component.find('form').simulate('submit', { preventDefault () {} });

      expect(testValues.handleSubmit).toBeCalledWith({
       bookersName: testValues.bookersName, workerName: testValues.workerName,
      bookingDate: testValues.bookingDate, service: testValues.service});
  });
});