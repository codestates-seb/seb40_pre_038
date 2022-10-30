import styled from 'styled-components';

const PageHeaderWrapper = styled.div`
  display: flex !important;
  margin-bottom: 12px !important;
  flex-wrap: wrap !important;
`;

const PageHeader = () => {
  return (
    <PageHeaderWrapper>
      <h1 className="flex--item fl1 fs-headline1 mr12 mb12">All Questions</h1>
      <div className="aside-cta flex--item mb12 print:d-none">
        <a href="/questions/ask" className="ws-nowrap s-btn s-btn__primary">
          Ask Question
        </a>
      </div>
    </PageHeaderWrapper>
  );
};

export default PageHeader;
