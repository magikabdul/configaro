import React from 'react';
import Navbar from 'components/molecules/Navbar/Navbar';

export default {
  title: 'Molecules/Navbar',
  component: Navbar,
  argTypes: {},
};

const Template = (args) => <Navbar {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
