import React from 'react';
import InputField from 'components/atoms/InputField/InputField';

export default {
  title: 'Atoms/InputField',
  component: InputField,
  argTypes: {
    type: {
      control: 'inline-radio',
    },
    value: {
      control: 'disabled',
    },
  },
};

const Template = (args) => <InputField {...args} />;

export const Text = Template.bind({});
Text.args = {
  id: 'login',
  type: 'text',
  label: 'Login',
  placeholder: 'Wprowadź login/email',
  error: '',
};

export const Password = Template.bind({});
Password.args = {
  id: 'password',
  type: 'password',
  label: 'Hasło',
  placeholder: 'Wprowadź hasło',
  error: '',
};

export const Error = Template.bind({});
Error.args = {
  id: 'login',
  type: 'text',
  label: 'Etykieta',
  placeholder: 'Wprowadź dane',
  error: 'Błędna wartość',
};
