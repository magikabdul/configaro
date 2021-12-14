import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  display: flex;
  align-items: center;
`;

const Check = styled.input`
  margin-right: 8px;
  width: 14px;
  height: 14px;
`;

const Label = styled.label`
  font-size: 12px;
`;

const Checkbox = ({ id, label, checked, onChange }) => (
  <Container>
    <Check id={id} type='checkbox' checked={checked} onChange={onChange} />
    <Label htmlFor={id}>{label}</Label>
  </Container>
);

Checkbox.propTypes = {
  checked: PropTypes.bool,
  id: PropTypes.string.isRequired,
  label: PropTypes.node,
  onChange: PropTypes.func,
};

Checkbox.defaultProps = {
  checked: undefined,
  label: 'label',
  onChange: undefined,
};

export default Checkbox;
