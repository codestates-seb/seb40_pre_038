import styled from 'styled-components';

const PageHeaderHeadline = styled.h1`
  font-size: 2.07692308rem !important;
  flex: 1 auto !important;

  &.mb12 {
    margin-bottom: 12px !important;
  }
  &.mr12 {
    margin-right: 12px !important;
  }

  @media (max-width: 640px) {
    font-size: 2rem !important;
  }
`;

export const Headline = ({ children, className }) => {
  return (
    <PageHeaderHeadline className={className}>{children}</PageHeaderHeadline>
  );
};
