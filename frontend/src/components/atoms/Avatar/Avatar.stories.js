import React from 'react';
import Avatar from 'components/atoms/Avatar/Avatar';

export default {
  title: 'Atoms/Avatar',
  component: Avatar,
  argTypes: {
    status: {
      control: 'inline-radio',
    },
  },
};

const Template = (args) => <Avatar {...args} />;

export const Unknown = Template.bind({});
Unknown.args = {
  firstName: 'Krzysztof',
  lastName: 'Cholewa',
};

export const Online = Template.bind({});
Online.args = {
  firstName: 'Krzysztof',
  lastName: 'Cholewa',
  status: 'online',
};

export const Offline = Template.bind({});
Offline.args = {
  firstName: 'Krzysztof',
  lastName: 'Cholewa',
  status: 'offline',
};
