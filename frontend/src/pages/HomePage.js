import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Typography from 'components/atoms/Typography/Typography';
import theme from 'theme/theme';
import { Link } from 'react-router-dom';

const Container = styled.div`
  margin: 100px auto;
  width: 70%;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Box = styled.div`
  margin: ${({ m }) => m && `${m}px ${m}px`};
  margin-left: ${({ mx }) => mx && `${mx}px`};
  margin-right: ${({ mx }) => mx && `${mx}px`};
  margin-top: ${({ my }) => my && `${my}px`};
  margin-bottom: ${({ my }) => my && `${my}px`};

  padding: ${({ p }) => p && `${p}px ${p}px`};
  padding-left: ${({ px }) => px && `${px}px`};
  padding-right: ${({ px }) => px && `${px}px`};
  padding-top: ${({ py }) => py && `${py}px`};
  padding-bottom: ${({ py }) => py && `${py}px`};

  flex: ${({ flex }) => flex && `${flex}`};
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
      <Typography variant='h1' size='80px' align='center' upperCase>
        configaro
      </Typography>
      <Typography align='center'>
        Ogólnie znana teza głosi, iż użytkownika może rozpraszać zrozumiała zawartość strony, kiedy
        ten chce zobaczyć sam jej wygląd. Jedną z mocnych stron używania Lorem Ipsum jest to, że ma
        wiele różnych „kombinacji” zdań, słów i akapitów, w przeciwieństwie do zwykłego: „tekst,
        tekst, tekst”, sprawiającego, że wygląda to „zbyt czytelnie” po polsku. Wielu webmasterów i
        designerów używa Lorem Ipsum jako domyślnego modelu tekstu i wpisanie w internetowej
        wyszukiwarce ‘lorem ipsum’ spowoduje znalezienie bardzo wielu stron, które wciąż są w
        budowie. Wiele wersji tekstu ewoluowało i zmieniało się przez lata, czasem przez przypadek,
        czasem specjalnie (humorystyczne wstawki itd).
      </Typography>
      <Link to='/login'>
        <button
          type='button'
          style={{ margin: '50px', width: '200px', padding: '20px 10px', cursors: 'pointer' }}
        >
          logowanie
        </button>
      </Link>
      <Box m='100'>
        <p style={{ color: theme.colors.danger }}>{message}</p>
      </Box>
    </Container>
  );
};

export default HomePage;
