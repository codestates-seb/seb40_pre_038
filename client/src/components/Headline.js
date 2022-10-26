import styled from 'styled-components';

export const Headline = ({ children }) => {
  return <PageHeaderHeadline>{children}</PageHeaderHeadline>;
};

const PageHeaderHeadline = styled.h1`
  font-size: 2.07692308rem !important;
  flex: 1 auto !important;

  @media (max-width: 640px) {
    font-size: 2rem !important;
  }
`;
