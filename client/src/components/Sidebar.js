import styled from 'styled-components';
import { ButtonSblue } from './Buttons';

const YellowBoxWrapper = styled.ul`
  box-sizing: border-box;
  width: 100%;
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  box-shadow: 0 1px 2px #0000000d, 0 1px 4px #0000000d, 0 2px 8px #0000000d;
  .borderTop {
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
  }
  .borderBottom {
    border-bottom-left-radius: 3px;
    border-bottom-right-radius: 3px;
    border-bottom: 1px solid #f1e5bc;
  }
`;
const YellowBoxTitle = styled.li`
  list-style: none;
  box-sizing: border-box;
  height: 41px;
  padding: 12px 15px;
  background-color: #fbf3d5;
  border: 1px solid #f1e5bc;
  font-size: 12px;
  font-weight: bold;
  color: #525960;
`;

const YellowBoxBody = styled.li`
  list-style: none;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  padding: 0px 16px;
  background-color: #fdf7e2;
  border-left: 1px solid #f1e5bc;
  border-right: 1px solid #f1e5bc;
  font-size: 13px;
  svg,
  span {
    margin: 0px 8px 0px 0px;
  }
  a {
    text-decoration: none;
    color: #232629;
  }
`;
const BodyWrapper = styled.div`
  display: flex;
  box-sizing: border-box;
  margin: 14px 0px;
  span {
    color: #6a737c;
    font-weight: 500;
  }
`;

const YellowBox = ({ children, title, borderTop, borderBottom }) => {
  return (
    <YellowBoxWrapper>
      <YellowBoxTitle className={borderTop}>{title}</YellowBoxTitle>
      <YellowBoxBody className={borderBottom}>{children}</YellowBoxBody>
    </YellowBoxWrapper>
  );
};

const YellowSidebar = () => {
  return (
    <>
      <YellowBox borderTop="borderTop" title="The Overflow Blog">
        <div>
          <BodyWrapper>
            <div>
              <svg
                aria-hidden="true"
                width="14"
                height="14"
                viewBox="0 0 14 14"
              >
                <path d="m11.1 1.71 1.13 1.12c.2.2.2.51 0 .71L11.1 4.7 9.21 2.86l1.17-1.15c.2-.2.51-.2.71 0ZM2 10.12l6.37-6.43 1.88 1.88L3.88 12H2v-1.88Z"></path>
              </svg>
            </div>
            <a href="https://stackoverflow.blog/2022/10/24/how-hardware-and-software-can-maximize-your-flow-states/?cb=1&_ga=2.128384396.1876">
              How hardware and software can maximize your flow states
            </a>
          </BodyWrapper>
          <BodyWrapper>
            <div>
              <svg
                aria-hidden="true"
                width="14"
                height="14"
                viewBox="0 0 14 14"
              >
                <path d="m11.1 1.71 1.13 1.12c.2.2.2.51 0 .71L11.1 4.7 9.21 2.86l1.17-1.15c.2-.2.51-.2.71 0ZM2 10.12l6.37-6.43 1.88 1.88L3.88 12H2v-1.88Z"></path>
              </svg>
            </div>
            <a href="https://stackoverflow.blog/2022/10/25/a-flight-simulator-for-developers-to-practice-real-world-challenges-and-surprises-ep-500/?cb=1&_ga=2.63952175.1876088991.1666564010-646836467.1663513134">
              A flight simulator for developers to practice real world
              challenges and...
            </a>
          </BodyWrapper>
        </div>
      </YellowBox>
      <YellowBox title="Featured on Meta">
        <div>
          <BodyWrapper>
            <div>
              <svg
                aria-hidden="true"
                className="svg-icon iconStackExchange"
                width="14"
                height="14"
                viewBox="0 0 18 18"
                fill="#6a737c"
              >
                <path d="M15 1H3a2 2 0 0 0-2 2v2h16V3a2 2 0 0 0-2-2ZM1 13c0 1.1.9 2 2 2h8v3l3-3h1a2 2 0 0 0 2-2v-2H1v2Zm16-7H1v4h16V6Z" />
              </svg>
            </div>
            <a href="https://meta.stackexchange.com/questions/383022/the-2022-community-a-thon-has-begun?cb=1">
              The 2022 Community-a-thon has begun!
            </a>
          </BodyWrapper>
          <BodyWrapper>
            <div>
              <svg
                aria-hidden="true"
                className="svg-icon iconStackExchange"
                width="14"
                height="14"
                viewBox="0 0 18 18"
                fill="#6a737c"
              >
                <path d="M15 1H3a2 2 0 0 0-2 2v2h16V3a2 2 0 0 0-2-2ZM1 13c0 1.1.9 2 2 2h8v3l3-3h1a2 2 0 0 0 2-2v-2H1v2Zm16-7H1v4h16V6Z" />
              </svg>
            </div>

            <a href="https://meta.stackexchange.com/questions/383026/mobile-app-infrastructure-being-decommissioned?cb=1">
              Mobile app infrastructure being decommissioned
            </a>
          </BodyWrapper>
          <BodyWrapper>
            <div>
              <svg
                aria-hidden="true"
                className="native svg-icon iconLogoGlyphMd"
                width="14"
                height="14"
                viewBox="0 0 32 37"
              >
                <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#6a737c" />
                <path
                  d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
                  fill="#6a737c"
                />
              </svg>
            </div>
            <a href="https://meta.stackoverflow.com/questions/420897/staging-ground-workflow-canned-comments?cb=1">
              Staging Ground Workflow: Canned Comments
            </a>
          </BodyWrapper>
          <BodyWrapper>
            <div>
              <svg
                aria-hidden="true"
                className="native svg-icon iconLogoGlyphMd"
                width="14"
                height="14"
                viewBox="0 0 32 37"
              >
                <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#6a737c" />
                <path
                  d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
                  fill="#6a737c"
                />
              </svg>
            </div>
            <a href="https://meta.stackoverflow.com/questions/406928/the-script-tag-is-being-burninated?cb=1">
              The [script] tag is being burninated
            </a>
          </BodyWrapper>
          <BodyWrapper>
            <div>
              <svg
                aria-hidden="true"
                className="native svg-icon iconLogoGlyphMd"
                width="14"
                height="14"
                viewBox="0 0 32 37"
              >
                <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#6a737c" />
                <path
                  d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
                  fill="#6a737c"
                />
              </svg>
            </div>

            <a href="https://meta.stackoverflow.com/questions/421038/the-ask-wizard-2022-has-graduated?cb=1">
              Ask Wizard Test Graduation
            </a>
          </BodyWrapper>
        </div>
      </YellowBox>
      <YellowBox borderBottom="borderBottom" title="Hot Meta Posts">
        <div>
          <BodyWrapper>
            <span>38</span>
            <a href="https://meta.stackoverflow.com/questions/421006/burninate-self-hosting?cb=1">
              Burninate [self-hosting]
            </a>
          </BodyWrapper>
        </div>
      </YellowBox>
    </>
  );
};

const GreyBoxWrapper = styled.ul`
  box-sizing: border-box;
  margin: 15px 0px;
  padding: 0;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  box-shadow: 0 1px 2px #0000000d, 0 1px 4px #0000000d, 0 2px 8px #0000000d;
  .borderTop {
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
  }
  .borderBottom {
    border-bottom-left-radius: 3px;
    border-bottom-right-radius: 3px;
    border-bottom: 1px solid #e3e6e8;
  }
`;
const GreyBoxTitle = styled.li`
  list-style: none;
  box-sizing: border-box;
  height: 44.5px;
  padding: 12px 15px;
  background-color: #f8f9f9;
  border: 1px solid #e3e6e8;
  font-size: 15px;
  font-weight: 500;
  color: #525960;
`;
const GreyBoxBody = styled.li`
  list-style: none;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  padding: 4px 15px;
  border-left: 1px solid #e3e6e8;
  border-right: 1px solid #e3e6e8;
  font-size: 13px;
  font-weight: 500;
`;
const ColumnWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  margin: 14px 0px;
  svg.spotSearch {
    fill: #379fef;
    margin: 10px;
  }
  p {
    color: #6a737c;
    margin: 5px 0px 15px 0px;
    width: 190px;
  }
`;
const RowWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  svg.iconEye {
    fill: #3a739d;
    margin: -1px 4px 0px 0px;
  }
  span {
    font-weight: 300;
    color: #3a739d;
  }
`;
const GreyBox = ({ children, title, borderTop, borderBottom }) => {
  return (
    <GreyBoxWrapper>
      <GreyBoxTitle className={borderTop}>{title}</GreyBoxTitle>
      <GreyBoxBody className={borderBottom}>{children}</GreyBoxBody>
    </GreyBoxWrapper>
  );
};

const GreySidebar = () => {
  return (
    <>
      <GreyBox
        title="Custom Filters"
        borderTop="borderTop"
        borderBottom="borderBottom"
      >
        <BodyWrapper>
          <a
            href="https://stackoverflow.com/questions?edited=true"
            className="blueLink"
          >
            Create a custom filter
          </a>
        </BodyWrapper>
      </GreyBox>
      <GreyBox
        title="Watched Tags"
        borderTop="borderTop"
        borderBottom="borderBottom"
      >
        <ColumnWrapper>
          <svg
            aria-hidden="true"
            className="spotSearch"
            width="48"
            height="48"
            viewBox="0 0 48 48"
          >
            <path
              opacity=".2"
              d="M29.22 38.1a3.4 3.4 0 0 1 4.81-4.82l8.81 8.81a3.4 3.4 0 0 1-4.81 4.81l-8.81-8.8Z"
            ></path>
            <path d="M18.5 5a1 1 0 1 0 0 2c.63 0 1.24.05 1.84.15a1 1 0 0 0 .32-1.98A13.6 13.6 0 0 0 18.5 5Zm7.02 1.97a1 1 0 1 0-1.04 1.7 11.5 11.5 0 0 1 5.44 8.45 1 1 0 0 0 1.98-.24 13.5 13.5 0 0 0-6.38-9.91ZM18.5 0a18.5 18.5 0 1 0 10.76 33.55c.16.57.46 1.12.9 1.57L40 44.94A3.5 3.5 0 1 0 44.94 40l-9.82-9.82c-.45-.45-1-.75-1.57-.9A18.5 18.5 0 0 0 18.5 0ZM2 18.5a16.5 16.5 0 1 1 33 0 16.5 16.5 0 0 1-33 0Zm29.58 15.2a1.5 1.5 0 1 1 2.12-2.12l9.83 9.83a1.5 1.5 0 1 1-2.12 2.12l-9.83-9.83Z"></path>
          </svg>
          <p>Watch tags to curate your list of questions.</p>
          <ButtonSblue width="110px" height="35px" fontSize="12px">
            <RowWrapper>
              <svg
                aria-hidden="true"
                className="iconEye"
                width="18"
                height="18"
                viewBox="0 0 18 18"
              >
                <path d="M9.06 3C4 3 1 9 1 9s3 6 8.06 6C14 15 17 9 17 9s-3-6-7.94-6ZM9 13a4 4 0 1 1 0-8 4 4 0 0 1 0 8Zm0-2a2 2 0 0 0 2-2 2 2 0 0 0-2-2 2 2 0 0 0-2 2 2 2 0 0 0 2 2Z"></path>
              </svg>
              <span>Watch a tag</span>
            </RowWrapper>
          </ButtonSblue>
        </ColumnWrapper>
      </GreyBox>
      <GreyBox
        title="Ignored Tags"
        borderTop="borderTop"
        borderBottom="borderBottom"
      >
        <ColumnWrapper>
          <RowWrapper>
            <ButtonSblue width="130px" height="35px" fontSize="12px">
              <span>Add an ignored tag</span>
            </ButtonSblue>
          </RowWrapper>
        </ColumnWrapper>
      </GreyBox>
      <GreyBox
        title="Collectives"
        borderTop="borderTop"
        borderBottom="borderBottom"
      >
        <BodyWrapper></BodyWrapper>
      </GreyBox>
    </>
  );
};

const GoogleAds = styled.img`
  display: none;
  @media screen and (min-width: 981px) {
    //981px ~
    display: block;
    width: 298px;
  }
`;
const HotNetworkQuestions = styled.div`
  display: flex;
  flex-direction: column;
  margin: 25px 0px 5px 0px;
  a.HNQTitle {
    text-align: left;
    text-decoration: none;
    color: black;
    font-size: 18px;
    font-weight: 500;
  }
`;
const HotNetworkLinks = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 14px 0px;
  a {
    text-align: left;
    text-decoration: none;
    color: #0074cc;
    font-size: 13px;
    margin: 5px 0px 5px 10px;
    &:hover {
      color: #379fef;
    }
  }
`;

const RecentQuestionsFeed = styled.div`
  display: flex;
  span {
    margin-left: 5px;
    font-size: 14px;
    cursor: pointer;
    color: #0074cc;
    &:hover {
      color: #379fef;
    }
  }
`;
const SidebarWrapper = styled.aside`
  .blueLink {
    text-decoration: none;
    color: #0074cc;
    &:hover {
      color: #379fef;
    }
  }
  @media screen and (min-width: 981px) {
    //981px ~
    width: 298px;
    margin-left: 25px;
  }
`;

const Sidebar = () => {
  return (
    <SidebarWrapper>
      <YellowSidebar />
      <GreySidebar />
      <GoogleAds
        src="https://tpc.googlesyndication.com/simgad/3248856506927570682"
        alt="stack overflow ads"
      />
      <HotNetworkQuestions>
        <a
          href="https://stackexchange.com/questions?tab=hot"
          className="HNQTitle"
        >
          Hot Network Questions
        </a>
        <HotNetworkLinks>
          <a href="https://travel.stackexchange.com/questions/177141/how-do-you-estimate-value-of-jewelry-for-travel-declaration">
            How do you estimate value of jewelry for travel declaration?
          </a>
          <a href="https://money.stackexchange.com/questions/153313/why-dont-unbanked-people-use-cryptocurrency">
            Why don&apos;t unbanked people use cryptocurrency?
          </a>
          <a href="https://codegolf.stackexchange.com/questions/253732/remove-a-given-substring-n-number-of-times-from-the-end-of-a-string">
            Remove a given substring &apos;n&apos; number of times from the end
            of a string
          </a>
          <a href="https://electronics.stackexchange.com/questions/639924/are-there-any-microcontrollers-that-do-not-require-an-external-clock-source">
            Are there any microcontrollers that do not require an external clock
            source?
          </a>
          <a href="https://travel.stackexchange.com/questions/177137/what-are-the-economics-of-black-market-money-exchanges">
            What are the economics of black market money exchanges?
          </a>
          <a href="https://tex.stackexchange.com/questions/662964/what-are-the-differences-between-primitives-registers-macros-tokens">
            What are the differences between primitives/registers/macros/tokens?
          </a>
          <a href="https://opensource.stackexchange.com/questions/13324/are-shell-scripts-considered-binaries-for-licensing-purposes">
            Are shell scripts considered binaries for licensing purposes?
          </a>
          <a href="https://philosophy.stackexchange.com/questions/94452/misconception-surrounding-kants-categorical-imperative">
            Misconception surrounding Kant&apos;s categorical imperative?
          </a>
          <a href="https://stats.stackexchange.com/questions/593450/why-do-we-need-so-many-dummy-variables-in-multivariable-linear-regression">
            Why do we need so many dummy variables in multivariable linear
            regression?
          </a>
          <a href="https://politics.stackexchange.com/questions/76305/are-power-plants-legitimate-military-targets-under-international-law">
            Are power plants legitimate military targets under international
            law?
          </a>
          <a href="https://academia.stackexchange.com/questions/189938/i-want-to-cite-a-theorem-from-a-book-written-by-influential-scientists-however">
            I want to cite a theorem from a book written by influential
            scientists. However the theorem is not proven in the book. Should I
            add a proof of my own?
          </a>
          <a href="https://diy.stackexchange.com/questions/259296/what-is-the-technical-term-for-the-rings-that-protect-holes-for-electrical-throu">
            What is the technical term for the rings that protect holes for
            electrical through woodwork like cabinets?
          </a>
          <a href="https://diy.stackexchange.com/questions/259271/is-it-normal-for-expanding-foam-insulation-to-contain-air-bubbles">
            Is it normal for expanding foam insulation to contain air bubbles?
          </a>
          <a href="https://money.stackexchange.com/questions/153289/if-an-international-bank-has-branches-in-two-countries-can-i-bank-transfer-betw">
            If an international bank has branches in two countries, can I bank
            transfer between them?
          </a>
          <a href="https://music.stackexchange.com/questions/125588/piano-scale-fingering-difference-between-one-and-multiple-octave-target">
            Piano scale fingering difference between one and multiple octave
            target
          </a>
          <a href="https://aviation.stackexchange.com/questions/95632/why-is-the-missed-approach-point-sometimes-beyond-the-threshold-or-runway-like-i">
            Why is the missed approach point sometimes beyond the threshold or
            runway like in the example below?
          </a>
          <a href="https://workplace.stackexchange.com/questions/188184/i-was-outsourced-can-former-co-worker-keep-requesting-passwords">
            I was outsourced. Can former co-worker keep requesting passwords?
          </a>
          <a href="https://unix.stackexchange.com/questions/722310/how-to-use-awk-to-extract-a-yaml-metadata-block">
            How to use awk to extract a YAML metadata block
          </a>
          <a href="https://unix.stackexchange.com/questions/722352/remove-all-words-that-counts-less-than-5-times-in-the-file">
            Remove all words that counts less than 5 times in the file
          </a>
          <a href="https://law.stackexchange.com/questions/85688/can-you-still-be-sued-for-copyright-infringement-if-you-comply-with-a-cease-and">
            Can you still be sued for copyright infringement if you comply with
            a cease and desist notice?
          </a>
          <a href="https://cooking.stackexchange.com/questions/122108/how-can-i-fix-a-liqudy-goug%c3%a8res-dough-for-piping">
            How can I fix a liqudy Gougères dough for piping?
          </a>
          <a href="https://mathoverflow.net/questions/433181/uniform-density-of-lipschitz-maps-is-space-of-continuous-function-for-general">
            Uniform density of Lipschitz maps is space of continuous function -
            for general metric spaces
          </a>
          <a href="https://ux.stackexchange.com/questions/144731/whats-a-good-way-to-display-settings-that-can-be-configured-from-both-directio">
            What&apos;s a good way to display settings that can be configured
            from both &quot;directions&quot;
          </a>
          <a href="https://mathematica.stackexchange.com/questions/275093/how-to-leave-only-rows-satisfying-a-particular-condition-from-a-table">
            How to leave only rows satisfying a particular condition from a
            table?
          </a>
        </HotNetworkLinks>
      </HotNetworkQuestions>
      <RecentQuestionsFeed>
        <div>
          <svg
            aria-hidden="true"
            className="fc-orange-400 svg-icon iconRss"
            width="18"
            height="18"
            viewBox="0 0 18 18"
          >
            <path
              d="M3 1a2 2 0 0 0-2 2v12c0 1.1.9 2 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2H3Zm0 1.5c6.9 0 12.5 5.6 12.5 12.5H13C13 9.55 8.45 5 3 5V2.5Zm0 5c4.08 0 7.5 3.41 7.5 7.5H8c0-2.72-2.28-5-5-5V7.5Zm0 5c1.36 0 2.5 1.14 2.5 2.5H3v-2.5Z"
              fill="#F48024"
            ></path>
          </svg>
        </div>
        <span>Recent questions feed</span>
      </RecentQuestionsFeed>
    </SidebarWrapper>
  );
};

export default Sidebar;
