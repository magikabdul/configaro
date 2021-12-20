import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Typography from 'components/atoms/Typography/Typography';
import theme from 'theme/theme';
import { Link } from 'react-router-dom';
import backgroundImage from 'assets/images/conference-room.jpg';
import Box from 'components/atoms/Box/Box';
import Button from 'components/atoms/Button/Button';

const Container = styled.div`
  margin: 0 auto;
  height: 100vh;
  width: 100%;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-end;

  &:before {
    position: absolute;
    content: '';
    width: 100%;
    height: 100%;
    background: url(${backgroundImage}) center/cover no-repeat;
    filter: brightness(40%);
  }
`;

const HomePage = () => {
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetch('/api/v1/health')
      .then((r) => r.text())
      .then((m) => setMessage(m));
  }, []);

  return (
    <Container>
      <Box mb={400} mx={300} style={{ position: 'relative' }}>
        <Typography
          style={{ position: 'relative' }}
          variant='h1'
          size='180px'
          align='center'
          upperCase
        >
          configaro
        </Typography>

        <Typography align='center'>
          Ut lobortis, tortor at blandit bibendum, dolor dolor elementum lorem, vel cursus tellus
          arcu ut nulla. Suspendisse ipsum lectus, finibus eget elementum sed, imperdiet eget
          sapien. Aenean pretium felis in tempor vehicula. In vitae faucibus nunc. Vestibulum
          efficitur consequat interdum. Class aptent taciti sociosqu ad litora torquent per conubia
          nostra, per inceptos himenaeos. Sed quis congue velit. Praesent a arcu sapien. Nulla
          pharetra lacinia sapien, non accumsan diam egestas ac. Integer vitae turpis sit amet mi
          sodales accumsan. Nullam mattis dolor in urna sagittis elementum. Curabitur risus ante,
          imperdiet at risus quis, vehicula placerat tellus. Nullam eu nibh rhoncus, gravida est
          non, convallis elit. Vivamus faucibus elementum sapien, id egestas nibh finibus eget.
          Donec dapibus dictum sollicitudin. Aenean eu sem viverra, laoreet sem id, fermentum urna.
        </Typography>
        <Box mt={50} style={{ display: 'flex', justifyContent: 'center' }}>
          <Link to='/login'>
            <Button align='center' color='info'>
              logowanie
            </Button>
          </Link>
        </Box>
        <Box mt={10} style={{ display: 'flex', justifyContent: 'center' }}>
          <p style={{ color: theme.colors.success }}>{message}</p>
        </Box>
      </Box>
    </Container>
  );
};

export default HomePage;
