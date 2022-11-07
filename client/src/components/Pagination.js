import styled from 'styled-components';

const PagerWrapper = styled.div`
  margin: 20px 0;
  display: flex;
  flex-wrap: wrap;
  float: left !important;
`;

export const Pagination = ({
  size,
  totalPages,
  currentPage,
  onClickPageSizer,
  onChangePager,
}) => {
  return (
    <>
      <PageSizer size={size} onClick={onClickPageSizer} />
      <PagerWrapper>
        <Pager
          totalPages={totalPages}
          currentPage={currentPage}
          onChange={onChangePager}
        />
      </PagerWrapper>
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

export const PageSizer = ({ size = 15, onClick }) => {
  const handleClickPagenationButton = (e) => {
    onClick(e.target.textContent);
  };

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

export const Pager = ({ totalPages = 1, currentPage = 1, onChange }) => {
  const handleClickPrevNextButton = (e) => {
    const { textContent } = e.target;
    if (textContent === 'Prev') {
      onChange(currentPage - 1);
      return;
    }
    onChange(currentPage + 1);
  };

  const handleClickPageNumberButton = (e) => {
    onChange(e.target.textContent);
  };

  const ellipsisButton = (
    <PagenationButton className="clear">…</PagenationButton>
  );

  const prevNextButton = (type) => (
    <PagenationButton
      title={`Go to ${type.toLowerCase()} page`}
      onClick={handleClickPrevNextButton}
    >
      {type}
    </PagenationButton>
  );

  const NumberButton = (num) => (
    <PagenationButton
      key={num}
      title={`Go to page ${num}`}
      onClick={handleClickPageNumberButton}
      className={currentPage === num ? 'is-selected' : ''}
    >
      {num}
    </PagenationButton>
  );

  const range = (start, stop, step) =>
    Array.from(
      { length: (stop - start) / step + 1 },
      (_, i) => start + i * step
    ).map((v) => NumberButton(v));

  return (
    <>
      {currentPage === 1 ? null : <>{prevNextButton('Prev')}</>}
      {totalPages < 7 ? <>{range(1, totalPages, 1)}</> : null}
      {totalPages > 6 && totalPages < 9 ? (
        currentPage >= 1 && currentPage <= 4 ? (
          <>
            {range(1, 5, 1)}
            {ellipsisButton}
            {NumberButton(totalPages)}
          </>
        ) : currentPage >= 5 && currentPage <= totalPages ? (
          <>
            {NumberButton(1)}
            {ellipsisButton}
            {range(totalPages - 4, totalPages, 1)}
          </>
        ) : null
      ) : null}
      {totalPages >= 9 ? (
        currentPage >= 1 && currentPage <= 4 ? (
          <>
            {range(1, 5, 1)}
            {ellipsisButton}
            {NumberButton(totalPages)}
          </>
        ) : currentPage >= totalPages - 3 && currentPage <= totalPages ? (
          <>
            {NumberButton(1)}
            {ellipsisButton}
            {range(totalPages - 4, totalPages, 1)}
          </>
        ) : (
          <>
            {NumberButton(1)}
            {ellipsisButton}
            {range(currentPage - 2, currentPage + 2, 1)}
            {ellipsisButton}
            {NumberButton(totalPages)}
          </>
        )
      ) : null}
      {currentPage === totalPages ? null : <>{prevNextButton('Next')}</>}
    </>
  );
};
