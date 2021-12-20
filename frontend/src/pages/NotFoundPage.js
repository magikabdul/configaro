import React from 'react';
import Typography from 'components/atoms/Typography/Typography';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import Button from 'components/atoms/Button/Button';
import Box from 'components/atoms/Box/Box';

const Container = styled.div`
  width: 100%;
  height: 100%;

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
      <Box mt={50}>
        <Button>strona główna</Button>
      </Box>
    </Link>
  </Container>
);

export default NotFoundPage;
