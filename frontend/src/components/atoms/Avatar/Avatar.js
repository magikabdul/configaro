import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const Circle = styled.div`
  width: 64px;
  height: 64px;

  border-radius: 50%;

  background-color: ${({ theme }) => theme.colors.paper};

  display: flex;
  justify-content: center;
  align-items: center;

  text-transform: uppercase;
  font-size: 24px;
  font-weight: bold;
`;

const StatusBox = styled.div`
  margin-left: 24px;
  text-transform: uppercase;
`;

const UserData = styled.div`
  font-size: 14px;
`;

const Status = styled.div`
  display: flex;
  font-size: 10px;
  white-space: pre-wrap;

  & > p {
    color: ${({ theme, status }) => {
      switch (status) {
        case 'online':
          return theme.colors.success;
        case 'offline':
          return theme.colors.error;

        default:
          return theme.colors.inactive;
      }
    }}
`;

const Avatar = ({ firstName, lastName, status }) => {
  return (
    <Container>
      <Circle>{firstName.slice(0, 1) + lastName.slice(0, 1)}</Circle>
      <StatusBox>
        <UserData>{`${firstName} ${lastName}`}</UserData>
        <Status status={status}>
          Status: <p>{status}</p>
        </Status>
      </StatusBox>
    </Container>
  );
};

Avatar.propTypes = {
  firstName: PropTypes.string.isRequired,
  lastName: PropTypes.string.isRequired,
  status: PropTypes.oneOf(['online', 'offline', 'unknown']),
};

Avatar.defaultProps = {
  status: 'unknown',
};

export default Avatar;
