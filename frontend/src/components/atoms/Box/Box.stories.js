import React from 'react';
import Box from 'components/atoms/Box/Box';

export default {
  title: 'Atoms/Box',
  component: Box,
  argTypes: {
    children: {
      control: 'none',
    },
  },
};

const Template = (args) => (
  <div style={{ backgroundColor: '#888' }}>
    <Box {...args} />
  </div>
);

export const MarginExample = Template.bind({});
MarginExample.args = {
  children: (
    <p>
      przykładowy <strong>paragraf</strong> z ustawionymi marginesami
    </p>
  ),
  backgroundColor: 'green',
  m: 100,
  mx: 20,
  my: 10,
  mt: 20,
  ml: 50,
  mr: 100,
  mb: 30,
};

export const PaddingExample = Template.bind({});
PaddingExample.args = {
  children: <p>przykładowy paragraf z ustawionym padding-iem</p>,
  backgroundColor: 'purple',
  p: 100,
  px: 20,
  py: 10,
  pt: 20,
  pl: 50,
  pr: 100,
  pb: 30,
};
