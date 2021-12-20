import React from 'react';
import styled from 'styled-components';
import Typography from 'components/atoms/Typography/Typography';
import { Link } from 'react-router-dom';
import loginFormBackground from 'assets/images/server-room.jpg';
import Box from 'components/atoms/Box/Box';
import InputField from 'components/atoms/InputField/InputField';
import Button from 'components/atoms/Button/Button';

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  height: 100%;
`;

const Container = styled.div`
  display: grid;
  grid-template-columns: 40% 60%;
  width: 70%;
  height: 80%;

  border-radius: 10px;
  overflow: hidden;
`;

const Section = styled.div`
  display: flex;
  flex-direction: column;
`;

const LeftSection = styled(Section)`
  background-color: ${({ theme }) => theme.colors.primary};
  height: 100%;
  padding: 100px 50px 0;
`;

const RightSection = styled(Section)`
  background: url(${loginFormBackground}) center/cover no-repeat;
`;

const LoginPage = () => (
  <Wrapper>
    <Container>
      <LeftSection>
        <Typography variant='h1' align='center' size='40px' upperCase color='blue'>
          configaro
        </Typography>
        <Box mt={50}>
          <Typography variant='h1' align='left' size='25px' color='black' upperCase>
            Zaloguj się
          </Typography>
          <Typography align='left' size='14px' color='gray'>
            Podaj login i hasło
          </Typography>
        </Box>

        <Box mt={20}>
          <InputField id='login' type='text' label='Login' placeholder='Podaj login/email' />
          <InputField id='password' type='password' label='Hasło' placeholder='Podaj hasło' />
          <Link to='/dashboard'>
            <Button variant='contained' color='info' fullWidth>
              zaloguj się
            </Button>
          </Link>
        </Box>
      </LeftSection>
      <RightSection />
    </Container>
  </Wrapper>
);

export default LoginPage;
