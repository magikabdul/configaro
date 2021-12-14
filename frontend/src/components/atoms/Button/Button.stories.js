import React from 'react';
import Button from 'components/atoms/Button/Button';

export default {
  title: 'Atoms/Button',
  component: Button,
  argTypes: {
    color: {
      control: 'inline-radio',
    },
    size: {
      control: 'inline-radio',
    },
    variant: {
      control: 'inline-radio',
    },
  },
};

const Template = (args) => <Button {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  color: 'primary',
  size: 'medium',
  variant: 'contained',
  fullWidth: false,
  children: 'button',
};

export const Secondary = Template.bind({});
Secondary.args = {
  color: 'secondary',
  size: 'medium',
  variant: 'contained',
  fullWidth: false,
  children: 'button',
};

export const Purple = Template.bind({});
Purple.args = {
  color: 'purple',
  size: 'medium',
  variant: 'contained',
  fullWidth: false,
  children: 'button',
};

export const Info = Template.bind({});
Info.args = {
  color: 'info',
  size: 'medium',
  variant: 'contained',
  fullWidth: false,
  children: 'button',
};

export const Error = Template.bind({});
Error.args = {
  color: 'error',
  size: 'medium',
  variant: 'contained',
  fullWidth: false,
  children: 'button',
};

export const Success = Template.bind({});
Success.args = {
  color: 'success',
  size: 'medium',
  variant: 'contained',
  fullWidth: false,
  children: 'button',
};

export const Warning = Template.bind({});
Warning.args = {
  color: 'warning',
  size: 'medium',
  variant: 'contained',
  fullWidth: false,
  children: 'button',
};
