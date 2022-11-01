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

const UqlNavWrapper = styled.div`
  display: flex !important;
  align-items: center !important;
  justify-content: space-between !important;
`;

const UqlNavigation = styled.div`
  margin-right: 16px !important;
  flex-flow: row nowrap !important;
  display: flex;
  margin-bottom: 1px;
`;

const UqlNavigationButton = styled.button`
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

const UqlNav = () => {
  const handleClickButton = (e) => {
    console.log(e.target.textContent);
  };

  return (
    <UqlNavWrapper>
      <UqlNavigation>
        <UqlNavigationButton
          className="is-selected"
          onClick={handleClickButton}
        >
          Newest
        </UqlNavigationButton>
        <UqlNavigationButton onClick={handleClickButton}>
          Unanswered
        </UqlNavigationButton>
        <UqlNavigationButton onClick={handleClickButton}>
          Score
        </UqlNavigationButton>
      </UqlNavigation>
    </UqlNavWrapper>
  );
};
