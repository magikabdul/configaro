import React from 'react';
import Snackbar from 'components/atoms/Snackbar/Snackbar';

export default {
  title: 'Atoms/Snackbar',
  component: Snackbar,
  argTypes: {
    severity: {
      control: 'inline-radio',
    },
    anchorOrigin: {
      horizontal: {
        control: 'inline-radio',
      },
      vertical: {
        control: 'inline-radio',
      },
    },
  },
};

const Template = (args) => <Snackbar {...args} />;

export const Default = Template.bind({});
Default.args = {
  message: 'This a default message',
  anchorOrigin: {
    vertical: 'top',
    horizontal: 'left',
  },
};

export const DefaultWithAutoHide = Template.bind({});
DefaultWithAutoHide.args = {
  message: 'This a default message with auto hide',
  autoHideDuration: 5000,
  anchorOrigin: {
    vertical: 'top',
    horizontal: 'left',
  },
};

export const Success = Template.bind({});
Success.args = {
  severity: 'success',
  message: 'This a success message',
  anchorOrigin: {
    vertical: 'top',
    horizontal: 'right',
  },
};

export const Error = Template.bind({});
Error.args = {
  severity: 'error',
  message: 'This a error message',
  anchorOrigin: {
    vertical: 'top',
    horizontal: 'left',
  },
};

export const Info = Template.bind({});
Info.args = {
  severity: 'info',
  message: 'This a info message',
  anchorOrigin: {
    vertical: 'top',
    horizontal: 'left',
  },
};

export const Warning = Template.bind({});
Warning.args = {
  severity: 'warning',
  message: 'This a warning message',
  anchorOrigin: {
    vertical: 'top',
    horizontal: 'left',
  },
};
