const Pagination = () => {
  return (
    <>
      <PageSizer />
      <Pager />
    </>
  );
};

export default Pagination;

export const PageSizer = () => {
  return (
    <div className="s-pagination site1 themed page-sizer float-right">
      <a
        href="/questions?tab=bounties&pagesize=15"
        title="Show 15 items per page"
        className="s-pagination--item is-selected"
        aria-current="true"
      >
        15
      </a>
      <a
        href="/questions?tab=bounties&pagesize=30"
        title="Show 30 items per page"
        className="s-pagination--item"
      >
        30
      </a>
      <a
        href="/questions?tab=bounties&pagesize=50"
        title="Show 50 items per page"
        className="s-pagination--item"
      >
        50
      </a>
      <span className="s-pagination--item s-pagination--item__clear">
        per page
      </span>
    </div>
  );
};

export const Pager = () => {
  return (
    <div className="s-pagination site1 themed pager float-left">
      <div className="s-pagination--item is-selected" aria-current="page">
        1
      </div>
      <a
        className="s-pagination--item js-pagination-item"
        href="/questions?tab=bounties&page=2"
        rel=""
        title="Go to page 2"
      >
        2
      </a>
      <a
        className="s-pagination--item js-pagination-item"
        href="/questions?tab=bounties&page=3"
        rel=""
        title="Go to page 3"
      >
        3
      </a>
      <a
        className="s-pagination--item js-pagination-item"
        href="/questions?tab=bounties&page=4"
        rel=""
        title="Go to page 4"
      >
        4
      </a>
      <a
        className="s-pagination--item js-pagination-item"
        href="/questions?tab=bounties&page=5"
        rel=""
        title="Go to page 5"
      >
        5
      </a>
      <div className="s-pagination--item s-pagination--item__clear">…</div>
      <a
        className="s-pagination--item js-pagination-item"
        href="/questions?tab=bounties&page=19"
        rel=""
        title="Go to page 19"
      >
        19
      </a>
      <a
        className="s-pagination--item js-pagination-item"
        href="/questions?tab=bounties&page=2"
        rel="next"
        title="Go to page 2"
      >
        {' '}
        Next
      </a>
    </div>
  );
};
