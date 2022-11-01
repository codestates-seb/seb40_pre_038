import styled from 'styled-components';

export const Pagination = ({ size, totalPages, currentPage }) => {
  return (
    <>
      <PageSizer size={size} />
      <Pager totalPages={totalPages} currentPage={currentPage} />
    </>
  );
};

const PageSizerWrapper = styled.div`
  color: #232629;
  margin: 20px 0;
  display: flex;
  flex-wrap: wrap;
  float: right !important;
`;

const PagenationButton = styled.button`
  text-decoration: none;
  margin-left: 2px;
  margin-right: 2px;
  padding: 0 8px;
  background-color: transparent;
  border-radius: 3px;
  border: 1px solid #d6d9dc;
  font-size: 13px;
  line-height: calc((13 + 12) / 13);
  color: #3b4045;
  cursor: pointer;

  &.is-selected {
    border-color: transparent;
    background-color: #f48224;
    color: #fff;
  }
  &.clear {
    color: inherit;
    border-color: transparent;
    background-color: transparent;
    cursor: text;
  }

  :hover {
    text-decoration: none;
  }

  :hover:not(.is-selected, .clear) {
    border-color: #babfc4;
    background-color: #d6d9dc;
    color: #0c0d0e;
  }
`;

const PageSizerSpan = styled.span`
  margin-left: 2px;
  margin-right: 2px;
  padding: 0 8px;
  border-radius: 3px;
  border: 1px solid #d6d9dc;
  font-size: 13px;
  line-height: calc((13 + 12) / 13);
  color: inherit;
  border-color: transparent;
  background-color: transparent;
`;

export const PageSizer = ({ size = 15 }) => {
  const handleClickPagenationButton = (e) => [
    console.log(e.target.textContent),
  ];

  return (
    <PageSizerWrapper>
      {[15, 30, 50].map((v) => (
        <PagenationButton
          key={v}
          title={`Show ${v} items per page`}
          className={size === v ? 'is-selected' : ''}
          onClick={handleClickPagenationButton}
        >
          {v}
        </PagenationButton>
      ))}
      <PageSizerSpan>per page</PageSizerSpan>
    </PageSizerWrapper>
  );
};

const PagerWrapper = styled.div`
  margin: 20px 0;
  display: flex;
  flex-wrap: wrap;
  float: left !important;
`;

export const Pager = ({ totalPages = 4, currentPage = 1 }) => {
  const handleClickPrevNextButton = (e) => {
    console.log(e.target.textContent);
  };

  const handleClickPageNumberButton = (e) => {
    console.log(e.target.textContent);
  };

  return (
    <PagerWrapper>
      {currentPage === 1 ? null : (
        <>
          <PagenationButton
            title="Go to prev page"
            onClick={handleClickPrevNextButton}
          >
            Prev
          </PagenationButton>
        </>
      )}
      <PagenationButton
        title="Go to page 1"
        onClick={handleClickPageNumberButton}
      >
        1
      </PagenationButton>

      {totalPages === 1 ? null : (
        <PagenationButton
          className={currentPage === totalPages ? 'is-selected' : ''}
          title={`Go to page ${totalPages}`}
        >
          {totalPages}
        </PagenationButton>
      )}
      {currentPage === totalPages ? null : (
        <>
          <PagenationButton
            rel="next"
            title="Go to next page"
            onClick={handleClickPrevNextButton}
          >
            Next
          </PagenationButton>
        </>
      )}
    </PagerWrapper>
  );
};
