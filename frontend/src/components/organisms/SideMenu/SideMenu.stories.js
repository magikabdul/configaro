import React from 'react';
import SideMenu from 'components/organisms/SideMenu/SideMenu';
import { AiOutlineDashboard } from 'react-icons/all';

export default {
  title: 'Organisms/SideMenu',
  component: SideMenu,
};

const menu = [
  { icon: <AiOutlineDashboard />, title: 'dashboard' },
  { icon: <AiOutlineDashboard />, title: 'vendors' },
  { icon: <AiOutlineDashboard />, title: 'projects' },
];

const Template = (args) => <SideMenu {...args} />;

export const Menu = Template.bind({});
Menu.args = {
  menuItems: menu,
};
