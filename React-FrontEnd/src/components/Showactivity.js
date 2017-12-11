import React, {Component} from 'react';
import PropTypes from 'prop-types';



class Showactivity extends Component {

    static propTypes = {
        activity: PropTypes.array.isRequired
    };

    render(){
        return (
            <div>
                <table className = "table table-hover">
                    <thead>
                    <tr>
                        <th>Serial Number</th>
                        <th>Username</th>
                        <th>Activity_Time</th>
                        <th>Activity</th>
                    </tr>
                    </thead>
                    <tbody>

                    {this.props.activity.map(tile => (
                        <tr key={tile.user_ID}>
                            <td> {tile.user_ID}</td>
                            <td> {tile.email}</td>
                            <td>{tile.activity_TIME}</td>
                            <td>{tile.activity}</td>

                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        );
    }


}
export default Showactivity;