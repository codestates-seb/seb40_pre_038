import styled from 'styled-components';

const FlexWrapper = styled.div`
  display: flex !important;
  margin-bottom: 16px !important;
  align-items: center !important;
`;

const Fl1 = styled.div`
  font-size: 1.30769231rem !important;
  flex: 1 auto !important;
`;

const Tabs = styled.div`
  display: flex !important;
  flex-wrap: wrap;
  margin-bottom: 1px;
`;

const Tab = styled.button`
  position: relative;
  display: inline-block;
  padding: 0.8em;
  border: 1px solid transparent;
  border-radius: 3px;
  background-color: transparent;
  outline: none;
  font-family: inherit;
  font-size: 13px;
  font-weight: normal;
  line-height: calc((13 + 2) / 13);
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  user-select: none;
  border-color: #9fa6ad;
  margin-bottom: -1px;
  white-space: nowrap;
  color: #6a737c;

  :first-child:not(:only-child) {
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
  }
  :not(:first-child):not(:last-child) {
    border-radius: 0;
  }
  :not(:last-child) {
    margin-right: -1px;
  }
  :last-child:not(:only-child) {
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
  }
  :hover,
  :focus,
  :active {
    text-decoration: none;
    color: #525960;
    background-color: #f8f9f9;
  }
  :active {
    z-index: 30;
    background: #f1f2f3;
  }
  :focus {
    box-shadow: 0 0 0 4px #1a232629;
    outline: none;
  }
  &.is-selected {
    border-color: #838c95;
    z-index: 25;
    color: #3b4045;
    background-color: #e3e6e8;
    box-shadow: none;
  }
  &.is-selected:focus {
    box-shadow: 0 0 0 4px #1a232629;
    outline: none;
  }
`;

// const BountyIndicatorSpan = styled.span`
//   color: #fff;
//   display: inline;
//   background-color: #0074cc;
//   padding: 0.2em 0.5em 0.25em;
//   margin-right: 5px;
//   font-size: 11px;
//   line-height: 1.3;
//   border-radius: 2px;
// `;

const SortTabs = () => {
  const handleClickTab = (e) => {
    console.log(e.target.textContent);
  };

  return (
    <FlexWrapper>
      <Fl1 />
      <div>
        <Tabs>
          {/* <Tab
            title="Questions that may be of interest to you based on your history and tag preference"
            onClick={handleClickTab}
          >
            Interesting
          </Tab> */}
          {/* <Tab title="Questions with an active bounty" onClick={handleClickTab}>
            <BountyIndicatorSpan>297</BountyIndicatorSpan> Bountied
          </Tab> */}
          <Tab
            className="is-selected"
            title="Questions with the most views, most answers, and highest score over the last few days"
            onClick={handleClickTab}
          >
            Hot
          </Tab>
          <Tab
            title="Questions with the most views, most answers, and highest score this week"
            onClick={handleClickTab}
          >
            Week
          </Tab>
          <Tab
            title="Questions with the most views, most answers, and highest score this month"
            onClick={handleClickTab}
          >
            Month
          </Tab>
        </Tabs>
      </div>
    </FlexWrapper>
  );
};

export default SortTabs;
