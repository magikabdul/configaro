import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  display: flex;
  justify-content: ${({ open }) => (open ? 'flex-start' : 'center')};
  align-items: center;

  height: 40px;

  border-radius: 3px;
  border-left: ${({ theme, selected }) =>
    selected ? `3px solid ${theme.colors.info}` : `3px solid transparent`};
  border-right: 3px solid transparent;

  transition: all 0.3s ease-in-out;

  &:hover {
    background-color: rgba(255, 255, 255, 0.1);
  }
`;

const IconBox = styled.div`
  margin-left: ${({ open }) => (open ? '16px' : '0')};
`;

const Title = styled.div`
  margin-left: 16px;
  text-transform: uppercase;
  font-size: 12px;
`;

const MenuItem = ({ Icon, title, open, selected, ...props }) => {
  return (
    <Container selected={selected} open={open} {...props}>
      <IconBox open={open}>
        <Icon style={{ width: '20', height: '20' }} />
      </IconBox>
      {open && <Title>{title}</Title>}
    </Container>
  );
};

MenuItem.propTypes = {
  Icon: PropTypes.element.isRequired,
  open: PropTypes.bool,
  selected: PropTypes.bool,
  title: PropTypes.string.isRequired,
};

MenuItem.defaultProps = {
  open: true,
  selected: false,
};

export default MenuItem;
