import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  padding: 20px;
  border-radius: 5px;
  background-color: ${({ theme }) => theme.colors.paper};
  box-shadow: rgba(0, 0, 0, 0.19) 0 10px 20px, rgba(0, 0, 0, 0.23) 0 6px 6px;
`;

const Paper = ({ children }) => {
  return <Container>{children}</Container>;
};

Paper.propTypes = {
  children: PropTypes.node,
};

Paper.defaultProps = {
  children: (
    <>
      <p>test paragraf</p>
      <div>eee</div>
    </>
  ),
};

export default Paper;
