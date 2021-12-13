import React from 'react';
import Typography from 'components/atoms/Typography/Typography';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Container = styled.div`
  margin: 100px auto;
  width: 70%;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const NotFoundPage = () => (
  <Container>
    <Typography variant='h1' size='80px' align='center' upperCase>
      404
    </Typography>
    <Typography variant='h2' size='50px' align='center' upperCase>
      strona nie została znaleziona
    </Typography>
    <Link to='/'>
      <button
        type='button'
        style={{ margin: '50px', width: '200px', padding: '20px 10px', cursors: 'pointer' }}
      >
        Storna główna
      </button>
    </Link>
  </Container>
);

export default NotFoundPage;
