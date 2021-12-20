import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  min-height: 98px;
`;

const Label = styled.label`
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 2px;
  color: ${({ theme }) => theme.colors.inactive};
`;

const Input = styled.input`
  padding: 8px 8px;
  outline: none;
  border-radius: 5px;
  border: ${({ theme, error }) =>
    error ? `2px solid ${theme.colors.error}` : `2px solid ${theme.colors.inactive}`};
  transition: border 0.3s ease-in-out;

  &:focus {
    border: ${({ theme, error }) =>
      error ? `2px solid ${theme.colors.error}` : `2px solid ${theme.colors.info}`};
  }
`;

const Error = styled.p`
  font-size: 14px;
  font-weight: bold;
  margin: 2px 0 0 0;
  color: ${({ theme }) => theme.colors.error};
`;

const InputField = ({ id, type, label, placeholder, error, value, onChange }) => (
  <Container>
    <Label htmlFor={id}>{label}</Label>
    <Input
      id={id}
      type={type}
      placeholder={placeholder}
      error={error}
      value={value}
      onChange={onChange}
    />
    <Error>{error}</Error>
  </Container>
);

InputField.propTypes = {
  id: PropTypes.string.isRequired,
  error: PropTypes.string,
  label: PropTypes.string.isRequired,
  onChange: PropTypes.func,
  placeholder: PropTypes.string.isRequired,
  type: PropTypes.oneOf(['text', 'password', 'email']),
  value: PropTypes.string,
};

InputField.defaultProps = {
  error: '',
  onChange: undefined,
  type: 'text',
  value: undefined,
};

export default InputField;
