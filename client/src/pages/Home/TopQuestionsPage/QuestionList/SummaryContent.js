import SMetaTags from './SMetaTags';
import SUserCard from './SUserCard';

const SummaryContent = () => {
  return (
    <div className="s-post-summary--content">
      <h3 className="s-post-summary--content-title">
        <a
          href="/questions/74189299/function-not-working-correctly-x-y-or-z"
          className="s-link"
        >
          Function not working correctly, x != (y or z)
        </a>
      </h3>
      <div className="s-post-summary--meta">
        <SMetaTags />
        <SUserCard />
      </div>
    </div>
  );
};

export default SummaryContent;
