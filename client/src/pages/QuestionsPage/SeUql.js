const SeUql = () => {
  return (
    <div
      data-controller="se-uql"
      data-se-uql-id=""
      data-se-uql-sanitize-tag-query="false"
    >
      <div className="d-flex ai-center jc-space-between mb12 sm:fd-column sm:ai-stretch">
        <div className="fs-body3 flex--item fl1 mr12 sm:mr0 sm:mb12">
          276 questions with bounties
        </div>
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
      </div>
      <form
        className="s-expandable ps-relative z-active"
        id="uql-form"
        data-se-uql-target="form"
        data-action="se-uql#navigate"
      >
        <input name="uqlId" type="hidden" />
        <div className="s-expandable--content">
          <div className="bg-black-050 ba bc-black-100 bar-sm mb16">
            <div className="px12 py16">
              <div className="d-flex gs32 fw-wrap">
                <div className="flex--item">
                  <fieldset className="d-flex gs8 gsy fd-column">
                    <legend className="flex--item s-label px0">
                      Filter by
                    </legend>
                    <div className="flex--item">
                      <div className="d-flex gs4 gsx ai-center">
                        <div className="flex--item">
                          <input
                            className="s-checkbox d-block"
                            type="checkbox"
                            name="filterId"
                            defaultValue="NoAnswers"
                            id="6b4be349-1c1b-40c8-80c3-2c2d3ddf7365"
                          />
                        </div>
                        <label
                          className="flex--item s-label fw-normal ws-nowrap"
                          htmlFor="6b4be349-1c1b-40c8-80c3-2c2d3ddf7365"
                          id="fa1c2521-9dd4-45ba-b41a-46decd30f2ae"
                        >
                          No answers
                        </label>
                      </div>
                    </div>
                    <div className="flex--item">
                      <div className="d-flex gs4 gsx ai-center">
                        <div className="flex--item">
                          <input
                            className="s-checkbox d-block"
                            type="checkbox"
                            name="filterId"
                            defaultValue="NoAcceptedAnswer"
                            id="968118b5-3ddc-4cdf-bbfd-a429170a7a1a"
                          />
                        </div>
                        <label
                          className="flex--item s-label fw-normal ws-nowrap"
                          htmlFor="968118b5-3ddc-4cdf-bbfd-a429170a7a1a"
                          id="29ced45d-17fe-4bb1-82e6-78e5cc541dd1"
                        >
                          No accepted answer
                        </label>
                      </div>
                    </div>
                    <div className="flex--item">
                      <div className="d-flex gs4 gsx ai-center">
                        <div className="flex--item">
                          <input
                            className="s-checkbox d-block"
                            type="checkbox"
                            name="filterId"
                            defaultValue="Bounty"
                            defaultChecked="checked"
                            id="54e9d939-a12a-44d5-a650-35ee8a5c1727"
                          />
                        </div>
                        <label
                          className="flex--item s-label fw-normal ws-nowrap"
                          htmlFor="54e9d939-a12a-44d5-a650-35ee8a5c1727"
                          id="8bc012d1-d69e-45bd-b2fb-6d97b5d8c19c"
                        >
                          Has bounty
                        </label>
                      </div>
                    </div>
                  </fieldset>
                </div>
                <div className="flex--item">
                  <fieldset className="d-flex gs8 gsy fd-column">
                    <legend className="flex--item s-label px0">
                      Sorted by
                    </legend>
                    <div className="flex--item">
                      <div className="d-flex gs4 gsx ai-center">
                        <div className="flex--item">
                          <input
                            className="s-radio d-block"
                            type="radio"
                            name="sortId"
                            defaultValue="Newest"
                            id="8182e744-351a-4ecb-8d75-e7e28faa4236"
                          />
                        </div>
                        <label
                          className="flex--item s-label fw-normal ws-nowrap"
                          htmlFor="8182e744-351a-4ecb-8d75-e7e28faa4236"
                          id="246e03e1-00b3-4920-bb04-bce9218b29f1"
                        >
                          Newest
                        </label>
                      </div>
                    </div>
                    <div className="flex--item">
                      <div className="d-flex gs4 gsx ai-center">
                        <div className="flex--item">
                          <input
                            className="s-radio d-block"
                            type="radio"
                            name="sortId"
                            defaultValue="RecentActivity"
                            id="caf61fa7-1082-476d-b1db-d9a551befc3a"
                          />
                        </div>
                        <label
                          className="flex--item s-label fw-normal ws-nowrap"
                          htmlFor="caf61fa7-1082-476d-b1db-d9a551befc3a"
                          id="120ee1a9-cc2d-4928-b08a-f8dd6b6f20f2"
                        >
                          Recent activity
                        </label>
                      </div>
                    </div>
                    <div className="flex--item">
                      <div className="d-flex gs4 gsx ai-center">
                        <div className="flex--item">
                          <input
                            className="s-radio d-block"
                            type="radio"
                            name="sortId"
                            defaultValue="MostVotes"
                            id="15ba206d-32de-4879-b072-0cdc20d29319"
                          />
                        </div>
                        <label
                          className="flex--item s-label fw-normal ws-nowrap"
                          htmlFor="15ba206d-32de-4879-b072-0cdc20d29319"
                          id="b9c66a9f-91b0-4a46-a407-ea0325f3c67f"
                        >
                          Highest score
                        </label>
                      </div>
                    </div>
                    <div className="flex--item">
                      <div className="d-flex gs4 gsx ai-center">
                        <div className="flex--item">
                          <input
                            className="s-radio d-block"
                            type="radio"
                            name="sortId"
                            defaultValue="MostFrequent"
                            id="3623208f-00dc-4e4b-829d-7ba3c70a6f6b"
                          />
                        </div>
                        <label
                          className="flex--item s-label fw-normal ws-nowrap"
                          htmlFor="3623208f-00dc-4e4b-829d-7ba3c70a6f6b"
                          id="b6fd6be8-72fd-4e74-9a60-7c06a94592bd"
                        >
                          Most frequent
                        </label>
                      </div>
                    </div>
                    <div className="flex--item">
                      <div className="d-flex gs4 gsx ai-center">
                        <div className="flex--item">
                          <input
                            className="s-radio d-block"
                            type="radio"
                            name="sortId"
                            defaultValue="BountyEndingSoon"
                            defaultChecked="checked"
                            id="e69e6385-6765-44cc-a6e0-4483bb8eb501"
                          />
                        </div>
                        <label
                          className="flex--item s-label fw-normal ws-nowrap"
                          htmlFor="e69e6385-6765-44cc-a6e0-4483bb8eb501"
                          id="515333c9-d4fa-425e-8719-4eee02b01c80"
                        >
                          Bounty ending soon
                        </label>
                      </div>
                    </div>
                  </fieldset>
                </div>
                <div className="flex--item">
                  <fieldset className="d-flex gs8 gsy fd-column">
                    <legend className="flex--item s-label px0">
                      Tagged with
                    </legend>
                    <div className="flex--item">
                      <div className="d-flex gs4 gsx ai-center">
                        <div className="flex--item">
                          <input
                            className="s-radio d-block"
                            type="radio"
                            name="tagModeId"
                            defaultValue="Watched"
                            id="ff00a9f3-2c88-47d7-85a3-ecca8c8c482e"
                          />
                        </div>
                        <label
                          className="flex--item s-label fw-normal ws-nowrap"
                          htmlFor="ff00a9f3-2c88-47d7-85a3-ecca8c8c482e"
                          id="205465b5-781d-44e7-a05e-9435702e25fc"
                        >
                          My watched tags
                        </label>
                      </div>
                    </div>
                    <div className="flex--item">
                      <div className="d-flex gs4 gsx ai-center">
                        <div className="flex--item">
                          <input
                            className="s-radio d-block"
                            type="radio"
                            name="tagModeId"
                            defaultValue="Specified"
                            defaultChecked="checked"
                            id="db065821-5882-4ba3-ac8d-008b0f3a25bb"
                          />
                        </div>
                        <label
                          className="flex--item s-label fw-normal ws-nowrap"
                          htmlFor="db065821-5882-4ba3-ac8d-008b0f3a25bb"
                          id="bca600eb-ec08-4fcb-92b6-3f674878881e"
                        >
                          The following tags:
                        </label>
                      </div>
                    </div>
                  </fieldset>
                  <div className="ps-relative ml24 mt8 ws2">
                    <input
                      id="uql-modal-tag-input"
                      className="w100 s-input"
                      name="tagQuery"
                      data-se-uql-target="tagQuery"
                      type="text"
                      size={60}
                      tabIndex={0}
                      aria-labelledby="bca600eb-ec08-4fcb-92b6-3f674878881e"
                      placeholder="e.g. javascript or python"
                      defaultValue=""
                      style={{ display: 'none' }}
                    />
                    <div
                      className="js-tag-editor tag-editor multi-line s-input"
                      style={{
                        padding: '0px 9.1px',
                        boxSizing: 'border-box',
                        marginTop: 0,
                        marginBottom: 0,
                        width: '100%',
                      }}
                    >
                      <span />
                      <input
                        type="text"
                        autoComplete="off"
                        tabIndex={0}
                        aria-autocomplete="list"
                        aria-haspopup="listbox"
                        // role="combobox"
                        // aria-expanded="false"
                        placeholder="e.g. javascript or python"
                        aria-labelledby="bca600eb-ec08-4fcb-92b6-3f674878881e"
                        className="s-input js-tageditor-replacing"
                        style={{ width: 19 }}
                      />
                      <span />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="p12 bt bc-black-100">
              <div className="d-flex">
                <div className="d-flex gs4 gsx fl1">
                  <button
                    className="s-btn s-btn__sm s-btn__primary flex--item"
                    type="submit"
                    data-se-uql-target="applyButton"
                  >
                    Apply filter
                  </button>
                  <button
                    className="s-btn s-btn__filled s-btn__sm flex--item"
                    // role="button"
                    data-action="se-uql#showSaveModal"
                  >
                    Save custom filter{' '}
                  </button>
                </div>
                <div className="flex--item">
                  <button
                    className="s-btn s-btn__sm"
                    data-action="se-uql#cancelEditor"
                    type="button"
                  >
                    Cancel
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
};

export default SeUql;
