const SortTab = () => {
  return (
    <div className="d-flex ai-center mb16">
      <div className="flex--item fl1 fs-body3" />
      <div className="flex--item">
        <div className=" d-flex s-btn-group js-filter-btn">
          <a
            className="js-sort-preference-change youarehere is-selected flex--item s-btn s-btn__muted s-btn__outlined"
            href="?tab=interesting"
            data-nav-xhref=""
            title="Questions that may be of interest to you based on your history and tag preference"
            data-value="interesting"
            data-shortcut=""
            aria-current="page"
          >
            Interesting
          </a>
          <a
            className="js-sort-preference-change flex--item s-btn s-btn__muted s-btn__outlined"
            href="?tab=bounties"
            data-nav-xhref=""
            title="Questions with an active bounty"
            data-value="bounties"
            data-shortcut="B"
          >
            <span className="bounty-indicator-tab">297</span> Bountied
          </a>
          <a
            className="js-sort-preference-change flex--item s-btn s-btn__muted s-btn__outlined"
            href="?tab=hot"
            data-nav-xhref=""
            title="Questions with the most views, most answers, and highest score over the last few days"
            data-value="hot"
            data-shortcut="H"
          >
            Hot
          </a>
          <a
            className="js-sort-preference-change flex--item s-btn s-btn__muted s-btn__outlined"
            href="?tab=week"
            data-nav-xhref=""
            title="Questions with the most views, most answers, and highest score this week"
            data-value="week"
            data-shortcut="W"
          >
            Week
          </a>
          <a
            className="js-sort-preference-change flex--item s-btn s-btn__muted s-btn__outlined"
            href="?tab=month"
            data-nav-xhref=""
            title="Questions with the most views, most answers, and highest score this month"
            data-value="month"
            data-shortcut="M"
          >
            Month
          </a>
        </div>
      </div>
    </div>
  );
};

export default SortTab;
