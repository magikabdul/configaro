import React, { useState } from 'react';
import styled from 'styled-components';
import Box from 'components/atoms/Box/Box';
import { BsBoxArrowInLeft, BsBoxArrowInRight } from 'react-icons/all';
import PropTypes from 'prop-types';

const Container = styled.div`
  height: calc(100vh - 40px);
  width: ${({ open }) => (open ? '200px' : '50px')};

  background-color: ${({ theme }) => theme.colors.paper};

  display: flex;
  flex-direction: column;

  transition: all 0.3s ease-in-out;
`;

const Logo = styled.div`
  padding-top: ${({ open }) => (open ? '24px' : '10px')};
  font-size: ${({ open }) => (open ? '30px' : '8px')};
  height: 90px;
  text-align: center;
  transition: all 0.3s ease-in-out;
`;

const ToggleButton = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 30px;
  color: ${({ theme }) => theme.colors.primary};
  background-color: transparent;
  border: none;
  border-top: 1px solid white;
  padding: 20px 0;
  cursor: pointer;
  transition: all 0.3s ease-in-out;

  &:hover {
    background-color: rgba(255, 255, 255, 0.1);
  }
`;

const IconBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 24px;
  height: 24px;
  transition: all 0.3s ease-in-out;
`;

const SideMenu = ({ menuItems }) => {
  const [open, setOpen] = useState(true);

  return (
    <Container open={open}>
      <Logo open={open}>CONFIGARO</Logo>
      <Box flex={1}>
        {menuItems.map((item) => (
          <p key={item.title}>{item.title}</p>
        ))}
      </Box>
      <Box>
        <ToggleButton onClick={() => setOpen(!open)}>
          <IconBox>
            {open ? (
              <BsBoxArrowInLeft style={{ width: 24, height: 24 }} />
            ) : (
              <BsBoxArrowInRight style={{ width: 24, height: 24 }} />
            )}
          </IconBox>
        </ToggleButton>
      </Box>
    </Container>
  );
};

SideMenu.propTypes = {
  menuItems: PropTypes.arrayOf(
    PropTypes.shape({
      icon: PropTypes.element,
      title: PropTypes.string,
    }),
  ).isRequired,
};

export default SideMenu;
