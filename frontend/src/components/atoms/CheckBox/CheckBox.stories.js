import React from 'react';
import Checkbox from 'components/atoms/CheckBox/CheckBox';

export default {
  title: 'Atoms/CheckBox',
  component: Checkbox,
  argTypes: {
    checked: {
      control: 'none',
    },
  },
};

const node = (
  <p>
    Jeżeli nie posiadasz <strong>konta</strong>, <em>zarejestruj</em> się
  </p>
);

const Template = (args) => <Checkbox {...args} />;

export const Checked = Template.bind({});
Checked.args = {
  id: 'rememberMe',
  label: 'Standardowy blok Lorem Ipsum, używany od XV wieku',
};

export const NotChecked = Template.bind({});
NotChecked.args = {
  id: 'rememberMe',
  label: node,
};
