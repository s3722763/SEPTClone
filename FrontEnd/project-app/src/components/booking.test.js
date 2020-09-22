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
      component.find('select').at(1).simulate('change', {target: {value: 'sandra'}})
      expect(component.find('select').at(1).props().value).toBe('sandra')
    });
    
    
})

describe('<Booking /> Submit form', () => {

  it('Submit should work', () => {
    const mockCallBack = jest.fn();

    const button = shallow((<Booking onSubmit={mockCallBack}>Submit!</Booking>));
    button.find('button').simulate('submit');
  });
});