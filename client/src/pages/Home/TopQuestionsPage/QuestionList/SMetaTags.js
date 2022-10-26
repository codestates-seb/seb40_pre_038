const SMetaTags = () => {
  return (
    <div className="s-post-summary--meta-tags tags js-tags t-python t-variables">
      <ul className="ml0 list-ls-none js-post-tag-list-wrapper d-inline">
        <li className="d-inline mr4 js-post-tag-list-item">
          <a
            href="/questions/tagged/python"
            className="post-tag flex--item mt0 js-tagname-python"
            title="show questions tagged 'python'"
            aria-label="show questions tagged 'python'"
            rel="tag"
            aria-labelledby="python-container"
          >
            python
          </a>
        </li>
        <li className="d-inline mr4 js-post-tag-list-item">
          <a
            href="/questions/tagged/variables"
            className="post-tag flex--item mt0 js-tagname-variables"
            title="show questions tagged 'variables'"
            aria-label="show questions tagged 'variables'"
            rel="tag"
            aria-labelledby="variables-container"
          >
            variables
          </a>
        </li>
      </ul>
    </div>
  );
};

export default SMetaTags;
