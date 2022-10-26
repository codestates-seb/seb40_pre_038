const SummaryStats = () => {
  return (
    <div className="s-post-summary--stats js-post-summary-stats">
      <div
        className="s-post-summary--stats-item s-post-summary--stats-item__emphasized"
        title="Score of 0"
      >
        <span className="s-post-summary--stats-item-number">0</span>
        <span className="s-post-summary--stats-item-unit">votes</span>
      </div>
      <div className="s-post-summary--stats-item  " title="0 answers">
        <span className="s-post-summary--stats-item-number">0</span>
        <span className="s-post-summary--stats-item-unit">answers</span>
      </div>
      <div className="s-post-summary--stats-item " title="3 views">
        <span className="s-post-summary--stats-item-number">3</span>
        <span className="s-post-summary--stats-item-unit">views</span>
      </div>
    </div>
  );
};

export default SummaryStats;
