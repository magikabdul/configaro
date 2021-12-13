import React from 'react';
import styled from 'styled-components';
import Typography from 'components/atoms/Typography/Typography';
import { Link } from 'react-router-dom';

const Container = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);

  margin: 200px auto;
  width: 50%;
  height: 50%;

  border-radius: 10px;
  overflow: hidden;
`;

const Box = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const LeftBox = styled(Box)`
  background-color: #7f0303;
`;

const RightBox = styled(Box)`
  background-color: white;
`;

const LoginPage = () => (
  <Container>
    <LeftBox>
      <Typography variant='h1' align='center' size='30px' upperCase>
        configaro
      </Typography>
    </LeftBox>
    <RightBox>
      <Link to='/'>
        <button
          type='button'
          style={{ margin: '50px', width: '200px', padding: '20px 10px', cursors: 'pointer' }}
        >
          zaloguj
        </button>
      </Link>
    </RightBox>
  </Container>
);

export default LoginPage;
