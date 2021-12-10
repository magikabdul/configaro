import React, { useEffect, useState } from 'react';
import GlobalStyle from 'theme/GlobalStyles';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/theme';
import MainWrapper from 'components/views/MainWrapper/MainWrapper';

const Root = () => {
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetch('/api/v1/health')
      .then((r) => r.text())
      .then((m) => setMessage(m));
  }, []);

  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <MainWrapper>
        <h1>Configaro</h1>
        <p style={{ color: theme.colors.primary }}>
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium
          commodi delectus esse fuga in necessitatibus nihil rem? Animi, ipsam
          quos?
        </p>
        <p style={{ color: theme.colors.secondary }}>
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium
          commodi delectus esse fuga in necessitatibus nihil rem? Animi, ipsam
          quos?
        </p>
        <p style={{ color: theme.colors.warning }}>
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium
          commodi delectus esse fuga in necessitatibus nihil rem? Animi, ipsam
          quos?
        </p>
        <p style={{ color: theme.colors.success }}>
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium
          commodi delectus esse fuga in necessitatibus nihil rem? Animi, ipsam
          quos?
        </p>
        <p style={{ color: theme.colors.danger }}>
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium
          commodi delectus esse fuga in necessitatibus nihil rem? Animi, ipsam
          quos?
        </p>
        <br />
        <p style={{ color: theme.colors.danger }}>{message}</p>
        <br />
        <button type='button'>sdgdsfh</button>
        <br />
        <input type='text' />
      </MainWrapper>
    </ThemeProvider>
  );
};

export default Root;
