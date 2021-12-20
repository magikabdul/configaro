import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import {
  FaGem,
  IoMdCheckmarkCircleOutline,
  MdClose,
  MdInfoOutline,
  MdOutlineWarningAmber,
  MdReportGmailerrorred,
} from 'react-icons/all';
import Box from 'components/atoms/Box/Box';

const Wrapper = styled.div`
  position: absolute;
  top: ${({ anchorOrigin: { vertical } }) => vertical === 'top' && '20px'};
  bottom: ${({ anchorOrigin: { vertical } }) => vertical === 'bottom' && '20px'};
  left: ${({ anchorOrigin: { horizontal } }) => horizontal === 'left' && '20px'};
  right: ${({ anchorOrigin: { horizontal } }) => horizontal === 'right' && '20px'};

  display: inline-flex;
  justify-content: flex-start;
  align-items: center;

  min-height: 48px;
  margin-top: 8px;
  padding: 8px 8px 8px 16px;

  border-radius: 5px;
  transition: all 0.3s ease-in-out;

  background-color: ${({ severity, theme }) => {
    return theme.colors[severity];
  }};
`;

const Container = styled.div`
  min-width: 200px;

  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const CloseButton = styled.button`
  margin-left: 8px;
  padding: 8px 8px;
  border: none;
  background: transparent;
  color: inherit;
  border-radius: 50%;
  transition: all 0.3s ease-in-out;
  cursor: pointer;

  &:hover {
    background-color: rgba(0, 0, 0, 0.1);
  }
`;

const getIcon = (severity) => {
  const style = {
    width: '24px',
    height: '24px',
    marginRight: '16px',
  };

  switch (severity) {
    case 'success':
      return <IoMdCheckmarkCircleOutline style={style} />;
    case 'warning':
      return <MdOutlineWarningAmber style={style} />;
    case 'error':
      return <MdReportGmailerrorred style={style} />;
    case 'info':
      return <MdInfoOutline style={style} />;
    default:
      return <FaGem style={style} />;
  }
};

const Snackbar = ({ severity, message, autoHideDuration, anchorOrigin }) => {
  const [open, setOpen] = useState(true);

  useEffect(() => {
    let timeoutId;
    if (autoHideDuration) timeoutId = setTimeout(() => setOpen(false), autoHideDuration);
    return () => clearTimeout(timeoutId);
  }, [open]);

  return (
    open && (
      <Wrapper severity={severity} anchorOrigin={anchorOrigin}>
        <Container>
          {getIcon(severity)}
          <Box flex={1}>{message}</Box>
          <CloseButton>
            <MdClose style={{ width: '24px', height: '24px' }} onClick={() => setOpen(false)} />
          </CloseButton>
        </Container>
      </Wrapper>
    )
  );
};

Snackbar.propTypes = {
  anchorOrigin: PropTypes.shape({
    horizontal: PropTypes.oneOf(['left', 'right']),
    vertical: PropTypes.oneOf(['top', 'bottom']),
  }),
  autoHideDuration: PropTypes.number,
  message: PropTypes.string.isRequired,
  severity: PropTypes.oneOf(['success', 'error', 'warning', 'info', 'inactive']),
};

Snackbar.defaultProps = {
  anchorOrigin: {
    horizontal: 'left',
    vertical: 'bottom',
  },
  autoHideDuration: null,
  severity: 'inactive',
};

export default Snackbar;
