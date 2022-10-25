const PageHeader = () => {
  return (
    <div className="d-flex">
      <h1 className="flex--item fl1 fs-headline1">Top Questions</h1>
      <div className="ml12 aside-cta flex--item print:d-none">
        <a href="/questions/ask" className="ws-nowrap s-btn s-btn__primary">
          Ask Question
        </a>
      </div>
    </div>
  );
};

export default PageHeader;
