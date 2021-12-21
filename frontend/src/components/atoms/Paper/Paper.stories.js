import React from 'react';
import Paper from 'components/atoms/Paper/Paper';

export default {
  title: 'Atoms/Paper',
  component: Paper,
  argTypes: {},
};

const Template = (args) => <Paper {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
