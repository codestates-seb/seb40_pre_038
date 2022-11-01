import styled from 'styled-components';

const Wrapper = styled.div`
  display: flex !important;
  margin-bottom: 12px !important;
  align-items: center !important;
  justify-content: space-between !important;

  @media (max-width: 640px) {
    align-items: stretch !important;
    flex-direction: column !important;
  }
`;

const QuestionsCount = styled.div`
  font-size: 1.30769231rem !important;
  margin-right: 12px !important;
  flex: 1 auto !important;

  @media (max-width: 640px) {
    margin-bottom: 12px !important;
    margin-right: 0 !important;
    font-size: 1.4rem !important;
  }
`;

const SeUql = ({ questionsCnt = 0 }) => {
  return (
    <Wrapper>
      <QuestionsCount>
        {questionsCnt.toLocaleString()} questions with bounties
      </QuestionsCount>
      <UqlNav />
    </Wrapper>
  );
};

export default SeUql;

const UqlNav = () => {
  return (
    <div
      className="uql-nav flex--item"
      data-action="se-uql-list:edit-current-requested@document->se-uql#toggleEditor"
    >
      <div className="d-flex ai-center jc-space-between">
        <div className="js-uql-navigation s-btn-group flex--item mr16 ff-row-nowrap">
          <a
            className="s-btn s-btn__muted s-btn__outlined s-btn__sm d-flex"
            data-nav-value="Newest"
            href="/questions?tab=Newest"
            data-shortcut="N"
          >
            <div className="flex--item">Newest</div>
          </a>
          <a
            className="s-btn s-btn__muted s-btn__outlined s-btn__sm d-flex"
            data-nav-value="Active"
            href="/questions?tab=Active"
            data-shortcut="A"
          >
            <div className="flex--item">Active</div>
          </a>
          <a
            className="s-btn s-btn__muted s-btn__outlined s-btn__sm d-flex is-selected uql-nav--expanded-item"
            data-nav-value="Bounties"
            href="/questions?tab=Bounties"
            data-shortcut="E"
            aria-current="page"
          >
            <div className="flex--item">Bountied</div>
            <div className="s-badge s-badge__bounty s-badge__bounty s-badge__sm lh-xs ml4 px4 flex--item">
              276
            </div>
          </a>
          <a
            className="s-btn s-btn__muted s-btn__outlined s-btn__sm d-flex uql-nav--expanded-item"
            data-nav-value="Unanswered"
            href="/questions?tab=Unanswered"
            data-shortcut="U"
          >
            <div className="flex--item">Unanswered</div>
          </a>
          <button
            className="s-btn s-btn__muted s-btn__outlined s-btn__sm s-btn__dropdown"
            // role="button"
            data-controller="s-popover"
            data-action="s-popover#toggle"
            data-se-uql-target="toggleMoreButton"
            aria-haspopup="true"
            aria-expanded="false"
            aria-controls="uql-more-popover"
          >
            More
          </button>
        </div>
        <div
          className="s-popover z-dropdown ws2 px0 py4"
          id="uql-more-popover"
          data-se-uql-target="morePopover"
        >
          <div className="s-popover--arrow" />
          <ul className="s-menu js-uql-navigation" role="menu">
            <li className=" uql-nav--collapsed-item" role="menuitem">
              <a
                href="/questions?tab=Bounties"
                className="s-block-link s-block-link__left is-selected"
                aria-current="page"
                data-shortcut=""
              >
                Bountied
                <span className="s-badge s-badge__bounty s-badge__bounty s-badge__sm lh-xs px4">
                  276
                </span>
              </a>
            </li>
            <li className=" uql-nav--collapsed-item" role="menuitem">
              <a
                href="/questions?tab=Unanswered"
                className="s-block-link s-block-link__left"
                data-shortcut=""
              >
                Unanswered
              </a>
            </li>
            <li role="menuitem">
              <a
                href="/questions?tab=Frequent"
                className="s-block-link s-block-link__left"
                data-shortcut="F"
              >
                Frequent
              </a>
            </li>
            <li role="menuitem">
              <a
                href="/questions?tab=Votes"
                className="s-block-link s-block-link__left"
                data-shortcut="V"
              >
                Score
              </a>
            </li>
            <li className="s-menu--divider" role="separator" />
            <li role="menuitem">
              <span className="s-block-link c-default fc-black-350">
                Unanswered (my tags)
              </span>
            </li>
            <li className="s-menu--divider" role="separator" />
            <li className="s-menu--title" role="separator">
              Custom filters
            </li>
            <li role="menuitem">
              <div className="py16 px24 ta-center fs-fine fc-black-500">
                Save custom sorting &amp; filtering for easy access.
              </div>
            </li>
          </ul>
        </div>
        <div className="flex--item">
          <button
            className="s-btn s-btn__filled s-btn__sm s-btn__icon ws-nowrap"
            // role="button"
            data-controller="s-expandable-control"
            data-s-expandable-control-toggle-class="is-selected"
            data-se-uql-target="toggleFormButton"
            aria-expanded="false"
            aria-controls="uql-form"
          >
            <svg
              aria-hidden="true"
              className="svg-icon iconFilter"
              width={18}
              height={18}
              viewBox="0 0 18 18"
            >
              <path d="M2 4h14v2H2V4Zm2 4h10v2H4V8Zm8 4H6v2h6v-2Z" />
            </svg>{' '}
            Filter
          </button>
        </div>
      </div>
    </div>
  );
};
