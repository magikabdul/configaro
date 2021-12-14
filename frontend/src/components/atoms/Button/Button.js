import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import Ripples from 'react-ripples';

const Container = styled.button`
  color: ${({ color, variant, theme }) => {
    if (variant === 'outlined' || variant === 'text') {
      switch (color) {
        case 'primary':
          return theme.colors.primary;
        case 'secondary':
          return theme.colors.secondary;
        case 'purple':
          return theme.colors.purple;
        case 'info':
          return theme.colors.info;
        case 'error':
          return theme.colors.error;
        case 'success':
          return theme.colors.success;
        case 'warning':
          return theme.colors.warning;
        default:
          return 'inherit';
      }
    } else {
      return color === 'warning' || color === 'primary'
        ? theme.colors.secondary
        : theme.colors.primary;
    }
  }};

  background-color: ${({ color, variant, theme }) => {
    if (variant === 'contained') {
      switch (color) {
        case 'primary':
          return theme.colors.primary;
        case 'secondary':
          return theme.colors.secondary;
        case 'purple':
          return theme.colors.purple;
        case 'info':
          return theme.colors.info;
        case 'error':
          return theme.colors.error;
        case 'success':
          return theme.colors.success;
        case 'warning':
          return theme.colors.warning;
        default:
          return 'inherit';
      }
    } else {
      return 'transparent';
    }
  }};

  border: ${({ color, variant, theme }) => {
    if (variant === 'outlined') {
      switch (color) {
        case 'primary':
          return `1px solid ${theme.colors.primary}`;
        case 'secondary':
          return `1px solid ${theme.colors.secondary}`;
        case 'purple':
          return `1px solid ${theme.colors.purple}`;
        case 'info':
          return `1px solid ${theme.colors.info}`;
        case 'error':
          return `1px solid ${theme.colors.error}`;
        case 'success':
          return `1px solid ${theme.colors.success}`;
        case 'warning':
          return `1px solid ${theme.colors.warning}`;
        default:
          return 'none';
      }
    } else {
      return '1px solid transparent';
    }
  }};

  padding: ${({ size }) => {
    switch (size) {
      case 'medium':
        return '8px 24px';
      case 'large':
        return '10px 28px';
      default:
        return '6px 20px';
    }
  }};

  width: ${({ fullWidth }) => fullWidth && '100%'};
  font-size: 14px;
  font-weight: bold;

  text-transform: uppercase;
  border-radius: 5px;
  cursor: pointer;

  transition: all 0.3s ease-in-out;
  overflow: hidden;

  &:hover {
    filter: brightness(85%);
  }
`;

const StyledRipple = styled(Ripples)`
  width: ${({ fullWidth }) => fullWidth && '100%'};
`;

const Button = ({ color, size, variant, fullWidth, ...props }) => {
  return (
    <StyledRipple fullWidth={fullWidth}>
      <Container color={color} size={size} variant={variant} fullWidth={fullWidth} {...props} />
    </StyledRipple>
  );
};

Button.propTypes = {
  color: PropTypes.oneOf(['primary', 'secondary', 'purple', 'info', 'error', 'success', 'warning']),
  size: PropTypes.oneOf(['small', 'medium', 'large']),
  variant: PropTypes.oneOf(['contained', 'outlined', 'text']),
  fullWidth: PropTypes.bool,
};

Button.defaultProps = {
  color: 'primary',
  size: 'medium',
  variant: 'contained',
  fullWidth: false,
};

export default Button;
