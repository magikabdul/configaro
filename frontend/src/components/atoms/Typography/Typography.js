import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.p`
  text-transform: ${({ upperCase }) => upperCase && `upperCase`};
  text-align: ${({ align }) => {
    switch (align) {
      case 'center':
      case 'left':
      case 'right':
      case 'justify':
        return `${align}`;
      default:
        return 'inherit';
    }
  }};

  color: ${({ color }) => color};
  font-size: ${({ size }) => size};
`;

const Typography = ({ align, color, size, variant, upperCase, children }) => {
  return (
    <>
      {variant !== 'text' && (
        <Container as={variant} align={align} color={color} size={size} upperCase={upperCase}>
          {children}
        </Container>
      )}
      {variant === 'text' && (
        <Container align={align} color={color} size={size} upperCase={upperCase}>
          {children}
        </Container>
      )}
    </>
  );
};

Typography.propTypes = {
  align: PropTypes.oneOf(['center', 'left', 'right', 'justify', 'inherit']),
  children: PropTypes.node.isRequired,
  color: PropTypes.string,
  size: PropTypes.string,
  upperCase: PropTypes.bool,
  variant: PropTypes.oneOf(['h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'text']),
};

Typography.defaultProps = {
  align: 'inherit',
  color: 'inherit',
  size: 'inherit',
  upperCase: false,
  variant: 'text',
};

export default Typography;
