import React from 'react';
import GlobalStyle from 'theme/GlobalStyles';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/theme';

const Root = () => (
  <ThemeProvider theme={theme}>
    <GlobalStyle />
    <h1>Configaro</h1>
    <p style={{ color: theme.colors.primary }}>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium
      commodi delectus esse fuga in necessitatibus nihil rem? Animi, ipsam quos?
    </p>
    <button type='button'>sdgdsfh</button>
    <br />
    <input type='text' />
  </ThemeProvider>
);

export default Root;
