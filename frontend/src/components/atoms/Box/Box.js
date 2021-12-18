import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const Container = styled.div`
  background-color: ${({ backgroundColor }) => backgroundColor};
  margin-top: ${({ m, my, mt }) => `${mt || my || m}px`};
  margin-left: ${({ m, mx, ml }) => `${ml || mx || m}px`};
  margin-bottom: ${({ m, my, mb }) => `${mb || my || m}px`};
  margin-right: ${({ m, mx, mr }) => `${mr || mx || m}px`};

  padding-top: ${({ p, py, pt }) => `${pt || py || p}px`};
  padding-left: ${({ p, px, pl }) => `${pl || px || p}px`};
  padding-bottom: ${({ p, py, pb }) => `${pb || py || p}px`};
  padding-right: ${({ p, px, pr }) => `${pr || px || p}px`};

  flex: ${({ flex }) => flex && `${flex}`};
`;

const Box = ({
  children,
  backgroundColor,
  flex,
  mt,
  mb,
  ml,
  mr,
  mx,
  my,
  m,
  pt,
  pb,
  pl,
  pr,
  px,
  py,
  p,
  ...props
}) => (
  <Container
    {...props}
    backgroundColor={backgroundColor}
    flex={flex}
    mt={mt}
    mb={mb}
    ml={ml}
    mr={mr}
    mx={mx}
    my={my}
    m={m}
    pt={pt}
    pb={pb}
    pl={pl}
    pr={pr}
    px={px}
    py={py}
    p={p}
  >
    {children}
  </Container>
);

Box.propTypes = {
  children: PropTypes.node,
  backgroundColor: PropTypes.string,
  flex: PropTypes.number,
  m: PropTypes.number,
  mx: PropTypes.number,
  my: PropTypes.number,
  mt: PropTypes.number,
  mr: PropTypes.number,
  mb: PropTypes.number,
  ml: PropTypes.number,
  p: PropTypes.number,
  px: PropTypes.number,
  py: PropTypes.number,
  pt: PropTypes.number,
  pr: PropTypes.number,
  pb: PropTypes.number,
  pl: PropTypes.number,
};

Box.defaultProps = {
  children: undefined,
  backgroundColor: 'inherit',
  flex: undefined,
  m: 0,
  mx: 0,
  my: 0,
  mt: 0,
  mr: 0,
  mb: 0,
  ml: 0,
  p: 0,
  px: 0,
  py: 0,
  pt: 0,
  pr: 0,
  pb: 0,
  pl: 0,
};

export default Box;
