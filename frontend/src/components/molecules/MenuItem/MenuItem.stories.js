import React from 'react';
import styled from 'styled-components';
import MenuItem from 'components/molecules/MenuItem/MenuItem';
import { AiOutlineDashboard } from 'react-icons/all';

export default {
  title: 'Molecules/MenuItem',
  component: MenuItem,
  argTypes: {
    icon: {
      control: false,
    },
  },
};

const WrapperWide = styled.div`
  width: 200px;
  height: 500px;
  background-color: #444444;
`;

const WrapperNarrow = styled.div`
  width: 50px;
  height: 500px;
  background-color: #444444;
`;

const TemplateWide = (args) => (
  <WrapperWide>
    <MenuItem {...args} />
  </WrapperWide>
);

const TemplateNarrow = (args) => (
  <WrapperNarrow>
    <MenuItem {...args} />
  </WrapperNarrow>
);

export const ItemWide = TemplateWide.bind({});

ItemWide.args = {
  title: 'dashboard',
  icon: <AiOutlineDashboard style={{ height: 20, width: 20 }} />,
  open: true,
  selected: false,
};

export const ItemNarrow = TemplateNarrow.bind({});

ItemNarrow.args = {
  title: 'dashboard',
  icon: <AiOutlineDashboard style={{ height: 20, width: 20 }} />,
  open: false,
  selected: false,
};
