import styled from 'styled-components';
import containerBackground from 'assets/svg/containerBackground.svg';

const MainWrapper = styled.div`
  margin: 30px;
  padding: 30px;

  height: calc(100vh - 60px);
  border-radius: 10px;
  background: url(${containerBackground}) no-repeat center/cover;
`;

export default MainWrapper;
