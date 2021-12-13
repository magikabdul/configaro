import React from 'react';
import GlobalStyle from 'theme/GlobalStyles';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/theme';
import MainWrapper from 'components/views/MainWrapper/MainWrapper';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import NotFoundPage from 'pages/NotFoundPage';
import HomePage from 'pages/HomePage';
import LoginPage from 'pages/LoginPage';

const Root = () => {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <MainWrapper>
        <BrowserRouter>
          <Routes>
            <Route path='/' exact element={<HomePage />} />
            <Route path='/login' exact element={<LoginPage />} />
            <Route path='*' element={<NotFoundPage />} />
          </Routes>
        </BrowserRouter>
      </MainWrapper>
    </ThemeProvider>
  );
};

export default Root;
