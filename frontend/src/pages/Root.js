import React from 'react';
import GlobalStyle from 'theme/GlobalStyles';
import { ThemeProvider } from 'styled-components';
import theme from 'theme/theme';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import NotFoundPage from 'pages/NotFoundPage';
import HomePage from 'pages/HomePage';
import LoginPage from 'pages/LoginPage';

const Root = () => {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <BrowserRouter>
        <Routes>
          <Route path='/' exact element={<HomePage />} />
          <Route path='/login' exact element={<LoginPage />} />
          <Route path='*' element={<NotFoundPage />} />
          <Route to='dashboard' />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
};

export default Root;
