import React from 'react';
import Typography from 'components/atoms/Typography/Typography';

export default {
  title: 'Atoms/Typography',
  component: Typography,
  argTypes: {
    variant: {
      control: 'inline-radio',
    },
    align: {
      control: 'inline-radio',
    },
  },
};

const Template = (args) => <Typography {...args} />;

export const Example = Template.bind({});
Example.args = {
  upperCase: true,
  variant: 'h1',
  children: 'There are many variations of passages of Lorem Ipsum available',
  align: 'center',
  color: '#fff',
  size: 'inherit',
};
