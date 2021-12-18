import { createGlobalStyle } from 'styled-components';
import backgroundImage from 'assets/svg/wickedbackground.svg';

const GlobalStyle = createGlobalStyle`
  *, *::before, *::after {
    box-sizing: border-box;
  }

  * {
    margin: 0;
    padding: 0;
  }

  body {
    line-height: 1.5;
    -webkit-font-smoothing: antialiased;
    font-family: 'Roboto', sans-serif;
    color: #fefefe;
    background: url(${backgroundImage}) no-repeat center/cover;
  }

  img, picture, video, canvas, svg {
    display: block;
    max-width: 100%;
  }

  input, button, textarea, select {
    font: inherit;
  }

  p, h1, h2, h3, h4, h5, h6 {
    overflow-wrap: break-word;
  }
  
  a {
    text-decoration: none;
  }

  #root, #__next {
    isolation: isolate;
  }
`;

export default GlobalStyle;
