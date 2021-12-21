import React from 'react';
import styled from 'styled-components';
import Avatar from 'components/atoms/Avatar/Avatar';

const Container = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;

  background-color: #111111;
  padding: 10px 20px;
`;

const Navbar = () => {
  const firstName = 'Joan';
  const lastName = 'Doe';
  const status = undefined;

  return (
    <Container>
      <Avatar firstName={firstName} lastName={lastName} status={status} />
    </Container>
  );
};

export default Navbar;
